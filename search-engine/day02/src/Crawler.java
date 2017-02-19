import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

public class Crawler {
    // the index where the results go
    private Index index;

    // queue of URLs to be indexed
    private Queue<String> queue = new LinkedList<>();

    // pages that we have processed
    private HashSet<String> seenPages = new HashSet<>();

    // queue of page urls that are potentially fetched
    private Queue<Future<HashSet<String>>> pendingPages = new LinkedList<>();

    // fetcher used to get pages from Wikipedia
    final static WikiFetcher wf = new WikiFetcher();

    ExecutorService crawlerPool;

    public Crawler(String source, Index index) {
        this.index = index;
        queue.offer(source);
        this.crawlerPool = Executors.newFixedThreadPool(10);
    }

    public int queueSize() {
        return queue.size();
    }

    /**
     * For all links in the queue, fetch the page using WikiFetcher and
     * call queueInternalLinks to enqueue all the hyperlinks into the queue.
     * For each page you come across, index the data on that page.
     * @param limit How many pages to crawl before you stop.
     * @throws IOException
     */
    public void crawl(int limit) throws IOException {
        System.out.println("Beginning crawl");
        while (seenPages.size() < limit) {
            // Spawn phase
            System.out.println("Spawn phase");
            long start = System.currentTimeMillis();
            while(!queue.isEmpty() && seenPages.size() < limit) {
                String url = queue.poll();
                if (!seenPages.contains(url)) {
                    seenPages.add(url);
                    System.out.println(seenPages.size() + " / " + limit);

                    // Get wiki links from page
                    Future<HashSet<String>> pageLinks = crawlerPool.submit(new CrawlerWorker(url, index));
                    pendingPages.add(pageLinks);
                }
            }
            System.out.println("Spawn phase duration: " + ((System.currentTimeMillis() - start) / 1000) + "s");

            // Wait phase
            System.out.println("Join phase");
            start = System.currentTimeMillis();
            while(!pendingPages.isEmpty()) {
                Future<HashSet<String>> pageLinks = pendingPages.poll();
                if (pageLinks.isDone()) {
                    try {
                        queue.addAll(pageLinks.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                } else {
                    pendingPages.add(pageLinks);
                }

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Join phase duration: " + ((System.currentTimeMillis() - start) / 1000) + "s");
        }
    }

    void queueInternalLinks(Elements paragraphs) {
        for (Element paragraph: paragraphs) {
            queueInternalLinks(paragraph);
        }
    }

    private void queueInternalLinks(Element paragraph) {

        Elements elts = paragraph.select("a[href]");
        for (Element elt: elts) {
            String relURL = elt.attr("href");

            if (relURL.startsWith("/wiki/")) {
                String absURL = "https://en.wikipedia.org" + relURL;
                queue.offer(absURL);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // make a WikiCrawler
        JedisPool jp = JedisMaker.makePool();
        Index index = new Index(jp);
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Crawler wc = new Crawler(source, index);

        // for testing purposes, load up the queue
//		Elements paragraphs = wf.fetchWikipedia(source);
//		wc.queueInternalLinks(paragraphs);

        // Crawl outward starting at source
        long start = System.currentTimeMillis();
        wc.crawl(10);
        long end = System.currentTimeMillis();
        System.out.println("Total crawl time: " + ((end - start) / 1000.0) + "s");
        jp.destroy();
        // TODO: Test that your index contains multiple pages.
        // Here is some sample code that tests your index, which assumes
        // you have written a getCounts() method in Index, which returns
        // a map from {url: count} for a given keyword
        Map<String, Integer> map = index.getCounts("programming");
        System.out.println("Found " + map.size() + " pages with the word 'programming'");
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry);
        }

        System.exit(0);
    }

    private class CrawlerWorker implements Callable {
        private String url;
        private Index index;

        public CrawlerWorker(String url, Index index) {
            this.url = url;
            this.index = index;
        }

        @Override
        public Object call() throws Exception {
            System.out.println("Crawler Worker (" + this.hashCode() + ") working on " + url);
            HashSet<String> links = new HashSet<>();
            long fetchStart = System.currentTimeMillis();
            Elements paragraphs = wf.fetchWikipedia(url);
            long fetchEnd = System.currentTimeMillis();
            System.out.println(this.hashCode() + " finished fetch");

            // Index this page
            long indexStart = System.currentTimeMillis();
            index.indexPage(url, paragraphs);
            long indexEnd = System.currentTimeMillis();
            System.out.println(this.hashCode() + " finished indexing");

            // Get all wiki links on page
            long linksStart = System.currentTimeMillis();
            for (Element paragraph: paragraphs) {
                Elements elts = paragraph.select("a[href]");
                for (Element elt: elts) {
                    String relURL = elt.attr("href");

                    if (relURL.startsWith("/wiki/")) {
                        String absURL = "https://en.wikipedia.org" + relURL;
                        links.add(absURL);
                    }
                }
            }
            long linksEnd = System.currentTimeMillis();
            System.out.println(this.hashCode() + " finished linking");
            System.out.println(this.hashCode() + " done. [ "
                    + ((fetchEnd - fetchStart) / 1000.0) + " | "
                    + ((indexEnd - indexStart) / 1000.0) + " | "
                    + ((linksEnd - linksStart) / 1000.0) + " ]");
            return links;
        }
    }
}
