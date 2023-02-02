package com.tradify_markets.tradify.route;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JWT {
    private final UserService userService;

    /*
     * Method code taken from Amigoscode:
     * https://www.youtube.com/watch?v=VVn9OG9nfH0
     */
    @GetMapping("/refresh")
    public void refresh(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String authorizationHeader = req.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                JWTVerifier verifier = com.auth0.jwt.JWT.require(
                        Algorithm.HMAC256("secret".getBytes())
                ).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String username = decodedJWT.getSubject();
                User user = userService.findByUsername(username);

                String accessToken = com.auth0.jwt.JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                        .withIssuer(req.getRequestURL().toString())
                        .withClaim("role", user.getRole().getName())
                        .sign(Algorithm.HMAC256("secret".getBytes()));

                Map<String, String> output = new HashMap<>();

                // Create access token with 15 minute duration
                output.put("access_token", accessToken);
                output.put("refresh_token", refreshToken);
                res.setContentType("application/json");
                new ObjectMapper().writeValue(res.getOutputStream(), output);

            } catch (Exception e) {
                res.setHeader("Error", e.getMessage());
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                res.setContentType("application/json");
                new ObjectMapper().writeValue(res.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
