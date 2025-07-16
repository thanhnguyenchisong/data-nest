package org.datanest.crawler;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.datanest.domain.RealEstate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@ApplicationScoped
public class RealEstateCrawlerService {

  public List<RealEstate> crawl() throws IOException {
    List<RealEstate> result = new ArrayList<>();
    Document doc = Jsoup.connect("https://batdongsan.vn/...").get();
    Elements posts = doc.select(".post-item");

    for (Element post : posts) {
      var estate = new RealEstate();
      estate.title = post.select(".title").text();
      estate.address = post.select(".address").text();
      estate.price = parsePrice(post.select(".price").text());
      estate.sourceUrl = post.select("a").attr("href");
      estate.dateCollected = LocalDateTime.now();
      result.add(estate);
    }
    return result;
  }

  private Long parsePrice(String priceText) {
    return Long.parseLong(priceText.replaceAll("[^0-9]", ""));
  }
}
