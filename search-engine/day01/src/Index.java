import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Index {

    // Index: map of words to URL and their counts
    private Map<String, Set<TermCounter>> index = new HashMap<String, Set<TermCounter>>();
    private Jedis jedis;

    public Index() {
        try {
            this.jedis = JedisMaker.make();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String term, TermCounter tc) {
        // if we're seeing a term for the first time, make a new Set
        // otherwise we can add the term to an existing Set
        index.putIfAbsent(term, new HashSet<TermCounter>());
        index.get(term).add(tc);
    }

    public Set<TermCounter> get(String term) {
        return index.get(term);
    }

    public void indexPage(String url, Elements paragraphs) {
        // make a TermCounter and count the terms in the paragraphs
        TermCounter termCounter = new TermCounter(url);
        termCounter.processElements(paragraphs);

        // for each term in the TermCounter, add the TermCounter to the index and store data on Redis
        termCounter.keySet().stream().forEach(term -> {
            add(term, termCounter);

            Transaction t = jedis.multi();
            String hashName = "TermCounter: " + url;
            String urlSetKey = "urlSet: " + term;
            int count = termCounter.get(term);
            t.hset(hashName, term, String.valueOf(count));
            t.sadd(urlSetKey, url);
            t.exec();
        });
    }

    public void printIndex() {
        // loop through the search terms
        for (String term: keySet()) {
            System.out.println(term);

            // for each term, print the pages where it appears
            Set<TermCounter> tcs = get(term);
            for (TermCounter tc: tcs) {
                Integer count = tc.get(term);
                System.out.println("    " + tc.getLabel() + " " + count);
            }
        }
    }

    public Set<String> keySet() {
        return index.keySet();
    }

    public static void main(String[] args) throws IOException {

        WikiFetcher wf = new WikiFetcher();
        Index indexer = new Index();

        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements paragraphs = wf.fetchWikipedia(url);
        indexer.indexPage(url, paragraphs);

        url = "https://en.wikipedia.org/wiki/Programming_language";
        paragraphs = wf.fetchWikipedia(url);
        indexer.indexPage(url, paragraphs);

        indexer.printIndex();
    }
}
