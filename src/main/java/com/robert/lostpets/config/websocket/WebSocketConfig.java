package com.robert.lostpets.config.websocket;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.robert.lostpets.config.security.service.TokenAuthenticationService;

/**
 * Clase de configuración para el WebSocket sobre el que está implementado el
 * sistema de mensajería basado en el protocolo STOMP.
 * 
 * @author Robert Ene
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue", "/exchange");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/lostpets-ws").setAllowedOrigins("*").withSockJS();
	}
	
	@Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    List<String> authHeader = accessor
                    		.getNativeHeader(TokenAuthenticationService.getAuthHeader());
                    if (authHeader == null || authHeader.isEmpty())
                    	return null;
                    Authentication auth = TokenAuthenticationService.getAuthentication(authHeader.get(0));
                    if (auth == null)
                    	return null;
                    accessor.setUser(auth);
                }
                return message;
            }
        });
    }
}
