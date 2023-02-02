package com.tradify_markets.tradify;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 * Class code taken from Amigoscode:
 * https://www.youtube.com/watch?v=VVn9OG9nfH0
 */

public class AuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                && !(request.getServletPath().equals("/login") ||
                request.getServletPath().equals("/register") ||
                request.getServletPath().equals("/jwt/refresh"))) {
            decodeJWT(filterChain, request, response, authorizationHeader);

            return;
        }

        filterChain.doFilter(request, response);
    }

    private void decodeJWT(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response, String authorizationHeader) throws IOException {
        try {
            JWTVerifier verifier = JWT.require(
                    Algorithm.HMAC256("secret".getBytes())
            ).build();
            DecodedJWT decodedJWT = verifier.verify(
                    authorizationHeader.substring("Bearer ".length())
            );

            String username = decodedJWT.getSubject();
            String role = decodedJWT.getClaim("role").asString();

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setHeader("Error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType("application/json");
            new com.fasterxml.jackson.databind.ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }
}
