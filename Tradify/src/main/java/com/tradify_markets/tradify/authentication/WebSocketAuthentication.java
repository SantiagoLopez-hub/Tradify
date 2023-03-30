package com.tradify_markets.tradify.authentication;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.Objects;

@AllArgsConstructor
public class WebSocketAuthentication {
    private final UserService userService;

    public ChannelInterceptor intercept(){
        return new ChannelInterceptor() {
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
    }
}
