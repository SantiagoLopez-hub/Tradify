package com.tradify_markets.tradify.route;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tradify_markets.tradify.model.Role;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.utility.LetterColour;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class JWTTest {
    private String testToken;
    private User testUser;

    @BeforeEach
    void setUp() {
        // Create a test role
        Role testRole = new Role();
        testRole.setName("USER");

        // Create a test user
        testUser = new User();
        testUser.setUsername("AdamTracie");
        testUser.setPassword("myS3cur3P4ssw0rd");
        testUser.setRole(testRole);

        // Create a new JWT token
        testToken = JWT.create()
                .withSubject(testUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .withClaim("role", testUser.getRole().getName())
                .sign(Algorithm.HMAC256("secret".getBytes()));

        testToken = "Bearer " + testToken;
    }

    @AfterEach
    void tearDown() {
        testToken = null;
        testUser = null;
    }

    @Test
    void refresh() {
        if (testToken != null && testToken.startsWith("Bearer ")) {
            String refreshToken = testToken.substring("Bearer ".length());
            JWTVerifier verifier = com.auth0.jwt.JWT.require(
                    Algorithm.HMAC256("secret".getBytes())
            ).build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);

            String username = decodedJWT.getSubject();
            User user = testUser.getUsername().equals(username) ? testUser : null;
            assert user != null;

            String accessToken = com.auth0.jwt.JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                    .withClaim("role", user.getRole().getName())
                    .sign(Algorithm.HMAC256("secret".getBytes()));

            DecodedJWT decodedJWT2 = verifier.verify(accessToken);
            String username2 = decodedJWT2.getSubject();
            Date expiration = decodedJWT2.getExpiresAt();
            User user2 = testUser.getUsername().equals(username2) ? testUser : null;

            // Check user exists and is the same as the original
            assert user2 != null;
            assert user2.getUsername().equals(user.getUsername());
            assert user2.getRole().getName().equals(user.getRole().getName());
            // Check expiration date exists and is 15 minutes from now
            assert expiration != null;
            assert expiration.getTime() - System.currentTimeMillis() <= 15 * 60 * 1000;
            System.out.println(
                    LetterColour.GREEN.getColour() +
                            "Refresh token test passed" +
                            LetterColour.RESET.getColour()
            );
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
