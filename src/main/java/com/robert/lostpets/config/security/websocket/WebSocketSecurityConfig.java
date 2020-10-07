package com.robert.lostpets.config.security.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Clase que define la capa de seguridad para el sistema de mensajería basado
 * en el protocolo STOMP (WebSockets).
 * 
 * @author Robert Ene
 */
@Configuration
public class WebSocketSecurityConfig
		extends AbstractSecurityWebSocketMessageBrokerConfigurer
		implements WebSocketMessageBrokerConfigurer {
	@Override
	protected void configureInbound(
			MessageSecurityMetadataSourceRegistry messages) {
		messages.simpDestMatchers("/lostpets-ws/**").authenticated();
	}

	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}
}
