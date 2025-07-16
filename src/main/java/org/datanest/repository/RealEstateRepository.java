package org.datanest.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.datanest.domain.RealEstate;

@ApplicationScoped
public class RealEstateRepository implements PanacheRepository<RealEstate> {
}