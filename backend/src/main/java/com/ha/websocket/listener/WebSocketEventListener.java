package com.ha.websocket.listener;

import com.ha.websocket.dto.SocketVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Component
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageSendingOperations;

    public WebSocketEventListener(SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection, user: {}", event.getUser());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Optional.ofNullable(headerAccessor.getSessionAttributes().get("username"))
                .map(String::valueOf)
                .ifPresent(this::sendMessage);
    }

    private Mono<Void> sendMessage(String username) {
        return Mono.just(
                SocketVO.builder()
                        .userName(username)
                        .content("회원 나간다..")
                        .build())
                .doOnNext(chat -> log.info("User Disconnected: {}", chat.getUserName()))
                .doOnNext(chat -> messageSendingOperations.convertAndSend("/topic/public", chat))
                .then();
    }
}
