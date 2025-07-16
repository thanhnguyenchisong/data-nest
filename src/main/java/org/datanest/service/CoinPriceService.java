package org.datanest.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanest.domain.CoinPrice;

@ApplicationScoped
public class CoinPriceService {

  @Inject
  org.datanest.repository.CoinPriceRepository repository;

  // Cache tất cả coin hiện tại (BTC, ETH, BNB, SOL, ...)
  private final Map<String, CoinPrice> latestPrices = new ConcurrentHashMap<>();

  // Update giá mới nhất cho 1 coin
  public void updatePrice(String symbol, double price) {
    CoinPrice coinPrice = new CoinPrice();
    coinPrice.symbol = symbol;
    coinPrice.price = price;
    coinPrice.timestamp = LocalDateTime.now();
    latestPrices.put(symbol, coinPrice);
  }

  // Trả về tất cả coin đang cache giá mới nhất
  public Collection<CoinPrice> getAllLatestPrices() {
    return latestPrices.values();
  }

  // Lưu toàn bộ cache vào DB định kỳ (scheduler gọi)
  public void saveAllHistory() {
    if (!latestPrices.isEmpty()) {
      repository.persist(latestPrices.values());
    }
  }
}
