package com.tradify_markets.tradify;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        req.getParameter("username"),
                        req.getParameter("password")
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        new ObjectMapper().writeValue(res.getOutputStream(), createTokens(authResult, req));
    }

    public String createToken(User user, HttpServletRequest req, int duration) {
        Algorithm alg = Algorithm.HMAC256("secret".getBytes());

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .withIssuer(req.getRequestURL().toString())
                .withClaim(
                        "roles",
                        user.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.toList())
                )
                .sign(alg);
    }

    public Map<String, String> createTokens(Authentication authResult, HttpServletRequest req) {
        User user = (User) authResult.getPrincipal();
        Map<String, String> output = new HashMap<>();

        // Create access token with 15 minute duration
        output.put("access_token", createToken(user, req,  15 * 60 * 1000));

        // Create refresh token with 1 day duration
        output.put("refresh_token", createToken(user, req, 24 * 60 * 60 * 1000));

        return output;
    }
}
