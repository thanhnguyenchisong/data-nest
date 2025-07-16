package org.datanest.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.datanest.websocket.CoinPriceWebSocket;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class BinanceWebSocketClient {

  private WebSocket webSocket;

  @PostConstruct
  public void init() {
    List<String> streams1 = List.of("btcusdt@trade", "ethusdt@trade", "bnbusdt@trade");
    List<String> streams2 = List.of("solusdt@trade", "adausdt@trade", "dotusdt@trade");

    connectBinance(streams1);
    connectBinance(streams2);
  }

  private void connectBinance(List<String> streams) {
    String streamParam = String.join("/", streams);
    String uri = "wss://stream.binance.com:9443/stream?streams=" + streamParam;
    HttpClient.newHttpClient().newWebSocketBuilder()
        .buildAsync(URI.create(uri), new BinanceListener());
  }

  @Override
  public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
    JSONObject json = new JSONObject(data.toString());
    JSONObject streamData = json.getJSONObject("data");
    String stream = json.getString("stream"); // btcusdt@trade
    String symbol = stream.split("@")[0].toUpperCase();
    double price = streamData.getDouble("p");

    CoinPriceService service = CoinPriceWebSocket.getService();
    service.updatePrice(symbol, price);
    CoinPriceWebSocket.broadcastAll(service.getAllLatestPrices());

    return WebSocket.Listener.super.onText(webSocket, data, last);
  }

}

