import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TermCounter {

	private Map<String, Integer> map;
	private String label;
	private int size;
	private HashSet<String> stopwords;

	public TermCounter(String label) {
		this.label = label;
		this.size = 0;
		this.map = new HashMap<String, Integer>();
	}

	public String getLabel() {
		return label;
	}

	public int size() {
		return size;
	}

	public void processElements(Elements paragraphs) {
		for (Node node: paragraphs) {
			processTree(node);
		}
	}

	public void processTree(Node root) {
		// NOTE: we could use select to find the TextNodes, but since
		// we already have a tree iterator, let's use it.
		for (Node node: new WikiNodeIterable(root)) {
			if (node instanceof TextNode) {
				processText(((TextNode) node).text());
			}
		}
	}

	public void processText(String text) {
		if (stopwords == null) {
		    stopwords = new HashSet<String>();
			String stopwordsFile = System.getProperty("user.dir") + "/" +
					"resources" + "/" + "stopwords.txt";

			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(stopwordsFile));
				while (true) {
					String line = br.readLine();
					if (line == null) break;
					stopwords.add(line);
				}
				br.close();
			} catch (IOException e1) {
				System.out.println("File not found: " + stopwordsFile);
			}
		}

		// replace punctuation with spaces, convert to lower case, and split on whitespace
		String[] array = text.replaceAll("\\pP", " ").toLowerCase().split("\\s+");

		for (int i = 0; i < array.length; i++) {
			if (stopwords == null || !stopwords.contains(array[i])) {
				incrementTermCount(array[i]);
			}
		}
	}

	public void incrementTermCount(String term) {
		put(term, map.getOrDefault(term, 0) + 1);
	}

	public void put(String term, int count) {
		size += count - map.getOrDefault(term, 0);
		map.put(term, count);
	}

	public Integer get(String term) {
		return map.getOrDefault(term, 0);
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	public void printCounts() {
		for (String key: keySet()) {
			Integer count = get(key);
			System.out.println(key + ", " + count);
		}
		System.out.println("Total of all counts = " + size());
	}

	public static void main(String[] args) throws IOException {
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

		WikiFetcher wf = new WikiFetcher();
		Elements paragraphs = wf.fetchWikipedia(url);

		TermCounter counter = new TermCounter(url.toString());
		counter.processElements(paragraphs);
		counter.printCounts();
	}
}
