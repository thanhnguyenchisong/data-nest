package org.datanest.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.json.bind.JsonbBuilder;
import org.datanest.domain.CoinPrice;
import org.datanest.websocket.CoinPriceWebSocket;

public class CoinScheduler {
//  @Scheduled(every = "10s")
//  void fetchCoinPrice() {
//    CoinPrice price = coinGeckoClient.fetch("bitcoin");
//    CoinService.update(price);
//    CoinPriceWebSocket.broadcast(JsonbBuilder.create().toJson(price));
//  }

}
