package com.example.gatewayservice.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/service01/**",
            "/service02/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            req -> openApiEndpoints.stream()
                    .noneMatch((url -> req.getURI().getPath().contains(url)));
}
