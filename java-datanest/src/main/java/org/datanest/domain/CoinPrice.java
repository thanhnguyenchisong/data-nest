package org.datanest.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class CoinPrice extends PanacheEntity {
  public String symbol; // BTC/USDT
  public Double price;
  public LocalDateTime timestamp;
}