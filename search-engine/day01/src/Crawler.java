import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
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
		this.crawlerPool = Executors.newFixedThreadPool(4);
	}

	public int queueSize() {
		return queue.size();
	}

	/**
	 * For all links in the queue, fetch the page using WikiFetcher and
	 * call queueInternalLinks to enqueue all the hyperlinks into the queue.
	 * For each page your come across, index the data on that page.
	 * @param limit How many pages to crawl before you stop.
	 * @throws IOException
	 */
	public void crawl(int limit) throws IOException {
		while (seenPages.size() < limit) {
			// Spawn phase
			while(!queue.isEmpty() && seenPages.size() < limit) {
				String url = queue.poll();
				if (!seenPages.contains(url)) {
					seenPages.add(url);
					Future<HashSet<String>> pageLinks = crawlerPool.submit(new CrawlerWorker(queue.poll()));
					pendingPages.add(pageLinks);
				}
			}

			// Wait phase
			while(!pendingPages.isEmpty()) {
				Future<HashSet<String>> pageLinks = pendingPages.poll();
				if (pageLinks.isDone()) {
					try {
						queue.addAll(pageLinks.get());
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				} else {
					pendingPages.add(pageLinks);
				}
			}
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
		Jedis jedis = JedisMaker.make();
		Index index = new Index();
		String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		Crawler wc = new Crawler(source, index);

		// for testing purposes, load up the queue
		Elements paragraphs = wf.fetchWikipedia(source);
		wc.queueInternalLinks(paragraphs);

        // TODO: Crawl outward starting at source

		// TODO: Test that your index contains multiple pages.
		// Here is some sample code that tests your index, which assumes
		// you have written a getCounts() method in Index, which returns
		// a map from {url: count} for a given keyword
		// Map<String, Integer> map = index.getCounts("programming");
		// for (Map.Entry<String, Integer> entry: map.entrySet()) {
		// 	System.out.println(entry);
		// }

	}

	private class CrawlerWorker implements Callable {
		private String url;

		public CrawlerWorker(String url) {
			this.url = url;
		}

		@Override
		public Object call() throws Exception {
			HashSet<String> links = new HashSet<>();
			// TODO: Return all links on page

			return links;
		}
	}
}
