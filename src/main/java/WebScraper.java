package main.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    public static class ScrapedData {
        public String title;
        public List<String> headings = new ArrayList<>();
        public List<String> links = new ArrayList<>();
    }

    public static ScrapedData scrape(String url) throws IOException {
        ScrapedData data = new ScrapedData();

        Document doc = Jsoup.connect(url).get();
        data.title = doc.title();

        for (int i = 1; i <= 6; i++) {
            Elements headings = doc.select("h" + i);
            for (Element heading : headings) {
                data.headings.add(heading.text());
            }
        }

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            data.links.add(link.absUrl("href"));
        }

        return data;
    }
}