package com.example.gatewayservice.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@RefreshScope
//@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();
        if (routerValidator.isSecured.test(req)) {
            return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
        }

        final String token = this.getAuthHeader(req);

        if (jwtUtil.isInvalid(token)) {
            return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
        }

        this.populateRequestWithHeaders(exchange, token);
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse res = exchange.getResponse();
        res.setStatusCode(httpStatus);
        return res.setComplete();
    }

    private String getAuthHeader (ServerHttpRequest req) {
        return req.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
}
