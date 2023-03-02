package com.example.cms9cc.websocket.client;

import com.example.cms9cc.template.bean.RateOddsItem;
import com.example.cms9cc.tripartite.SoccerInfoRealTime;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
public class StompClientConfiguration {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public StompClientConfiguration(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        WebSocketTransport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        ArrayList<Transport> transports = new ArrayList<>();
        transports.add(webSocketTransport);
        WebSocketClient client = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

     new Thread(new Runnable() {
         @Override
         public void run() {
             StompSession stompSession = null;

             CompletableFuture<StompSession> stompSessionCompletableFuture =
                     stompClient.connectAsync("ws://154.23.238.34/socket/match", new RootStompHandler());

             try {
                 stompSession = stompSessionCompletableFuture.get();
             } catch (InterruptedException | ExecutionException e) {
                 e.printStackTrace();
             }

             if (stompSession == null) {
                 System.out.println("连接数据中心失败");
                 return;
             }
             
             stompSession.subscribe("/topic/rate_odds", new StompFrameHandler() {
                 @Override
                 public Type getPayloadType(StompHeaders headers) {
                     return RateOddsItem.class;
                 }

                 @Override
                 public void handleFrame(StompHeaders headers, Object payload) {
                     RateOddsItem rateOddsItem = (RateOddsItem) payload;
                     simpMessagingTemplate.convertAndSend("/topic/match" + rateOddsItem.getMatch_id(), rateOddsItem);
                 }
             });
             stompSession.subscribe("/topic/soccerInfo", new StompFrameHandler() {
                 @Override
                 public Type getPayloadType(StompHeaders headers) {
                     return SoccerInfoRealTime.class;
                 }

                 @Override
                 public void handleFrame(StompHeaders headers, Object payload) {
                     SoccerInfoRealTime soccerInfoRealTime = (SoccerInfoRealTime) payload;
                     simpMessagingTemplate.convertAndSend("/topic/soccerInfo" + soccerInfoRealTime.getMatch_id(), soccerInfoRealTime);
                 }
             });
         }
     }).start();
    }
}
