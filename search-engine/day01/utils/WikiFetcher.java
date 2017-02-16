import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiFetcher {
	private long lastRequestTime = -1;
	private long minInterval = 1000;

	public Elements fetchWikipedia(String url) throws IOException {
//		sleepIfNeeded();

        // connect to the url
		Connection conn = Jsoup.connect(url);

        Document doc = repeatedGet(conn, 5);
//		Document doc = conn.get();

		// retrieve the portion of the html we care about
		Element content = doc.getElementById("mw-content-text");

		Elements paras = content.select("p");
		return paras;
	}

	/*
	    Attempt to get a document from a connection. If the operation times out. try again.
	    Keep trying for @param limit times. If none of the attempts succeeds, throw the timeout exception.
	 */
	private Document repeatedGet(Connection con, int limit) throws IOException {
        Document doc;
        try {
            doc = con.get();
            return doc;
        } catch (SocketTimeoutException e) {
            if (limit > 1) {
                System.out.println("Retrying " + con);
                return repeatedGet(con, limit - 1);
            } else {
                throw e;
            }
        }
    }

	public Elements readWikipedia(String url) throws IOException {
		URL realURL = new URL(url);

		// assemble the file name
		String slash = File.separator;
		String filename = "resources" + slash + realURL.getHost() + realURL.getPath();

		// read the file
		InputStream stream = WikiFetcher.class.getClassLoader().getResourceAsStream(filename);
		Document doc = Jsoup.parse(stream, "UTF-8", filename);

		// parse the contents of the file
		Element content = doc.getElementById("mw-content-text");
		Elements paras = content.select("p");
		return paras;
	}

	private void sleepIfNeeded() {
		if (lastRequestTime != -1) {
			long currentTime = System.currentTimeMillis();
			long nextRequestTime = lastRequestTime + minInterval;
			if (currentTime < nextRequestTime) {
				try {
					Thread.sleep(nextRequestTime - currentTime);
				} catch (InterruptedException e) {
					System.err.println("Warning: sleep interrupted in fetchWikipedia.");
				}
			}
		}
		lastRequestTime = System.currentTimeMillis();
	}

	public static void main(String[] args) throws IOException {
		WikiFetcher wf = new WikiFetcher();
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		Elements paragraphs = wf.readWikipedia(url);

		for (Element paragraph: paragraphs) {
			System.out.println(paragraph);
		}
	}
}
