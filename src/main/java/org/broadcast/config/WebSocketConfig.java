package org.broadcast.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(ConfigValues.WEB_SOCKET_URL)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker(ConfigValues.TOPIC_URL);
        config.setApplicationDestinationPrefixes(ConfigValues.APP_URL);
    }
}
