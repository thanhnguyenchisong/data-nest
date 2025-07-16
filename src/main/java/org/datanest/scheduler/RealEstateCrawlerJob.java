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
//    List<RealEstate> estates = crawler.crawl();
//    for (RealEstate estate : estates) {
//      service.save(estate);
//    }
//    String fileName = "real-estate/" + LocalDateTime.now() + ".json";
//    minio.upload(fileName, estates.toString().getBytes());
  }
}
