package org.datanest.api;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.datanest.crawler.RealEstateCrawlerService;
import org.datanest.domain.RealEstate;
import org.datanest.service.RealEstateService;

@Path("/api/real-estate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RealEstateResource {

  @Inject
  RealEstateService service;

  @Inject
  RealEstateCrawlerService crawlerService;

  @GET
  public List<RealEstate> list() {
    return service.listAll();
  }

  @POST
  public Response create(RealEstate estate) {
    estate.dateCollected = LocalDateTime.now();
    service.save(estate);
    return Response.status(Response.Status.CREATED).entity(estate).build();
  }

  @Path("/api/realestate/crawl")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public Response ingest(List<RealEstateDTO> data) {
    for (RealEstateDTO dto : data) {
      repository.persist(new RealEstate(dto.title, dto.price, dto.url));
    }
    return Response.ok().build();
  }

}

