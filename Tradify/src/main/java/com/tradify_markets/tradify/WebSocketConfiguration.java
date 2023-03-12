package com.tradify_markets.tradify;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Objects;

@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    private final UserService userService;

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        ChannelInterceptor channelInterceptor = new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                assert accessor != null;

                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String encodedJWT = Objects.requireNonNull(accessor.getNativeHeader("Authorization"))
                            .get(0)
                            .substring("Bearer ".length());

                    JWTVerifier verifier = com.auth0.jwt.JWT.require(
                            Algorithm.HMAC256("secret".getBytes())
                    ).build();
                    DecodedJWT decodedJWT = verifier.verify(encodedJWT);

                    String username = decodedJWT.getSubject();
                    User user = userService.findByUsername(username);
                    assert user != null;
                }

                return message;
            }
        };

        registration.interceptors(channelInterceptor);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-connection")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/stocks/order-book");
        registry.enableSimpleBroker("/subscribe-orders");
    }
}
