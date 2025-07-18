package org.datanest.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.datanest.domain.CoinPrice;

@ApplicationScoped
public class CoinPriceRepository implements PanacheRepository<CoinPrice> {
}