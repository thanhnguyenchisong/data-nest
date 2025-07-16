package org.datanest.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;
import org.datanest.domain.CoinPrice;
import org.datanest.repository.CoinPriceRepository;

@Path("/api/history")
public class CoinApiResource {

  @Inject
  CoinPriceRepository repository;

  @GET
  public List<CoinPrice> getHistory() {
    return repository.listAll();
  }
}
