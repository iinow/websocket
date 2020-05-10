package com.ha.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * enableSimpleBroker 를 설정할 경우 말 그대로 simpleBroker 이기 때문에 브로커가 1개의 토픽으로만 발행한다. 설정하지 말자
     *
     * setUserDestinationPrefix 은 @SubscribeMapping 어노테이션을 사용할 때에만 사용한다.
     * */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker()
//        registry.setUserDestinationPrefix("/sub");
//        registry.setApplicationDestinationPrefixes("/pub"); //서버가 클라이언트한테 메시지를 보낼때 접두어로 붙이게됨
    }

    /**
     * registry.addEndpoint("/web") 로 설정했을 경우
     *
     * const serverURL = 'http://192.168.0.15:8081/web'
     * let socket = new SockJS(serverURL)
     * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/web")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
