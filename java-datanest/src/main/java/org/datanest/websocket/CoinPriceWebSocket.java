package org.datanest.websocket;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.datanest.domain.CoinPrice;
import org.datanest.service.CoinPriceService;

@ServerEndpoint("/ws/price")
public class CoinPriceWebSocket {

  static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
  private static final Jsonb jsonb = JsonbBuilder.create();

  @Inject
  CoinPriceService service;

  @OnOpen
  public void onOpen(Session session) {
    sessions.add(session);
  }

  @OnClose
  public void onClose(Session session) {
    sessions.remove(session);
  }

  public static void broadcast(CoinPrice price) {
    String json = JsonbBuilder.create().toJson(price);
    sessions.forEach(session -> session.getAsyncRemote().sendText(json));
  }


  public static void broadcastAll(Collection<CoinPrice> prices) {
    String json = jsonb.toJson(prices);
    sessions.forEach(session -> session.getAsyncRemote().sendText(json));
  }

  public static CoinPriceService getService() {
    return CDI.current().select(CoinPriceService.class).get();
  }
}
