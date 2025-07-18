package org.datanest.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.datanest.domain.RealEstate;
import org.datanest.repository.RealEstateRepository;

@ApplicationScoped
public class RealEstateService {

  @Inject
  RealEstateRepository repository;

  public List<RealEstate> listAll() {
    return repository.listAll();
  }

  public void save(RealEstate entity) {
    repository.persist(entity);
  }
}
