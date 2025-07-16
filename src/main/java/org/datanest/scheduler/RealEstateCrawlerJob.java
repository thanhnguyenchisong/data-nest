package org.datanest.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.datanest.crawler.RealEstateCrawlerService;
import org.datanest.service.RealEstateService;
import org.datanest.utils.MinioService;

@ApplicationScoped
@Slf4j
public class RealEstateCrawlerJob {

  @Inject
  RealEstateCrawlerService crawler;

  @Inject
  RealEstateService service;

  @Inject
  MinioService minio;

  @Scheduled(every = "24h")
  void crawlAndSave() throws IOException {
    log.info("RealEstateCrawlerJob.crawlAndSave");
//    RealEstate estate = new RealEstate();
//    estate.title = "Căn hộ Vinhomes demo " + System.currentTimeMillis();
//    estate.price = "5 tỷ";
//    estate.location = "Q1, TP.HCM";
//    estate.thumbnail = "https://via.placeholder.com/150";
//    estate.sourceUrl = "https://batdongsan.com.vn/demo-url";
//    estate.sourceSite = "batdongsan.com.vn";
//    service.save(estate);
  }
}
