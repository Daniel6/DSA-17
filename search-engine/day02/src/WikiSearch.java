import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class WikiSearch {

    // map from URLs that contain the term(s) to relevance score
    private Map<String, Integer> map;

    public WikiSearch(Map<String, Integer> map) {
        this.map = map;
    }

    public Integer getRelevance(String url) {
        return map.get(url);
    }

    // Prints the contents in order of term frequency.
    private  void print() {
        List<Entry<String, Integer>> entries = sort();
        for (Entry<String, Integer> entry: entries) {
            System.out.println(entry);
        }
    }

    // Computes the union of two search results.
    public WikiSearch or(WikiSearch that) {
        Map<String, Integer> myMap = new HashMap<>();
        map.entrySet().parallelStream().forEach(entry -> {
            myMap.put(entry.getKey(), totalRelevance(entry.getValue(), that.getRelevance(entry.getKey())));
        });
        that.map.entrySet().parallelStream().forEach(entry -> {
            if (!myMap.containsKey(entry.getKey())) {
                myMap.put(entry.getKey(), totalRelevance(entry.getValue(), getRelevance(entry.getKey())));
            }
        });
        return new WikiSearch(myMap);
    }

    // Computes the intersection of two search results.
    public WikiSearch and(WikiSearch that) {
        Map<String, Integer> myMap = new HashMap<>();
        map.entrySet().parallelStream().forEach(entry -> {
            if (that.contains(entry.getKey())) {
                myMap.put(entry.getKey(), totalRelevance(entry.getValue(), that.getRelevance(entry.getKey())));
            }
        });
        return new WikiSearch(myMap);
    }

    public boolean contains(String url) {
        return map.containsKey(url);
    }

    // Computes the intersection of two search results.
    public WikiSearch minus(WikiSearch that) {
        Map<String, Integer> myMap = new HashMap<>();
        map.entrySet().parallelStream().forEach(entry -> {
            if (!that.contains(entry.getKey())) {
                myMap.put(entry.getKey(), totalRelevance(entry.getValue(), that.getRelevance(entry.getKey())));
            }
        });
        return new WikiSearch(myMap);
    }

    // Computes the relevance of a search with multiple terms.
    protected int totalRelevance(Integer rel1, Integer rel2) {
        return rel1 + rel2;
    }

    public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
    {
        return (o1.getValue() ).compareTo(o2.getValue());
    }

    // Sort the results by relevance.
    public List<Entry<String, Integer>> sort() {

        Stream<Entry<String,Integer>> sorted =
                map.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        List<Entry<String,Integer>> result = sorted.collect(Collectors.toList());

        return result;
    }



    // Performs a search and makes a WikiSearch object.
    public static WikiSearch search(String term) {
        Map<String, Integer> myMap = new HashMap<>();

        try (Jedis jedis = JedisMaker.make()) {

            Set<String> s = jedis.smembers("urlSet: " + term);
            s.parallelStream().forEach(url -> {
                int count = Integer.valueOf(jedis.hget("TermCounter: " + url, term));
                myMap.put(url, count);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Store the map locally in the WikiSearch
        return new WikiSearch(myMap);
    }

    // TODO: Choose an extension and add your methods here

    public static void main(String[] args) throws IOException {

        // search for the first term
        String term1 = "java";
        System.out.println("Query: " + term1);
        WikiSearch search1 = search(term1);
        search1.print();

        // search for the second term
        String term2 = "programming";
        System.out.println("Query: " + term2);
        WikiSearch search2 = search(term2);
        search2.print();

        // compute the intersection of the searches
        System.out.println("Query: " + term1 + " AND " + term2);
        WikiSearch intersection = search1.and(search2);
        intersection.print();
    }
}