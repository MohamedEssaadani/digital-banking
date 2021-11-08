package com.essaadani.securityservice.sec.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // get username & password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // create auth token with user infos
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // tell authenticationManager to let user authenticate
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // get authenticated user
        User  user = (User) authResult.getPrincipal();

        //get the algorithm to use to sign (signature) the jwt
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        // user roels / authorities
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // access token
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                // 15 MIN
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);

        // refresh token
        // will be used after the expiration of access token,  user will send the refresh token if its correct then we will send another access token
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                // 60 DAYS
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400L * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);


        // to send access & refresh tokens in the body
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        // content type of response is json
        response.setContentType(APPLICATION_JSON_VALUE);

        // Object Mapper used to serialize an object in json format
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
