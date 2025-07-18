package org.datanest.scheduler;


import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.datanest.service.CoinPriceService;

@ApplicationScoped
public class SaveHistoryScheduler {

  @Inject
  CoinPriceService service;

  @Scheduled(every = "60s")
  void persistPrice() {
    service.saveAllHistory();
  }
}
