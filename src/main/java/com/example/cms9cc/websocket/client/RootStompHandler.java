package com.example.cms9cc.websocket.client;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

public class RootStompHandler implements StompSessionHandler {
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("RootStompHandler.afterConnected");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println("RootStompHandler.handleTransportError"+exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        System.out.println("RootStompHandler.getPayloadType");
        return null;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("RootStompHandler.handleFrame");
    }
}
