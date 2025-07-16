package org.datanest.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.datanest.domain.RealEstate;
import org.datanest.crawler.RealEstateCrawlerService;
import org.datanest.service.RealEstateService;
import org.datanest.utils.MinioService;

@ApplicationScoped
public class RealEstateCrawlerJob {

  @Inject
  RealEstateCrawlerService crawler;

  @Inject
  RealEstateService service;

  @Inject
  MinioService minio;

  @Scheduled(every = "24h")
  void crawlAndSave() throws IOException {
    List<RealEstate> estates = crawler.crawl();
    for (RealEstate estate : estates) {
      service.save(estate);
    }
    String fileName = "real-estate/" + LocalDateTime.now() + ".json";
    minio.upload(fileName, estates.toString().getBytes());
  }
}
