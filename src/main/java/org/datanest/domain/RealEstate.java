package org.datanest.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RealEstate extends PanacheEntity {
  public String title;
  public String address;
  public Long price;
  public String sourceUrl;
  public LocalDateTime dateCollected;
}