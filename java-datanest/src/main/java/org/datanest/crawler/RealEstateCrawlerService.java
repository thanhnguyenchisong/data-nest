package org.datanest.crawler;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
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

  public String crawl() throws IOException {
    List<RealEstate> result = new ArrayList<>();
    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false)); // Chạy real UI

    BrowserContext context = browser.newContext(new Browser.NewContextOptions()
        .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36")
        .setViewportSize(1280, 720)
        .setJavaScriptEnabled(true)
    );
    Page page = context.newPage();
    page.navigate("https://batdongsan.com.vn/nha-dat-ban-hiep-hoa-bg");
    page.waitForLoadState(LoadState.NETWORKIDLE);
    String html = page.content();
    System.out.println(html);
    Document doc = Jsoup.parse(html);
    Elements posts = doc.select(".js__product-link-for-product-id");
    for (Element post : posts) {
      var estate = new RealEstate();
      estate.title = post.attr(".title");
      estate.location = post.select(".address").text();
      estate.price = post.select(".re__card-config-price").text();
      estate.area = post.select(".re__card-config-area").text();
      estate.pricePerM2 = post.select(".re__card-config-price_per_m2").text();
      estate.sourceUrl = post.attr("href");
      estate.dateCollected = LocalDateTime.now();
      result.add(estate);
    }
    return html;
  }

  private Long parsePrice(String priceText) {
    return Long.parseLong(priceText.replaceAll("[^0-9]", ""));
  }
}
