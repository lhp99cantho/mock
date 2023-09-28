package com.example.gatewayservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/fallback1")
    public String userFallback() {
        return "User service is not available";
    }

    @GetMapping("/fallback2")
    public String authFallback() {
        return "Auth service is not available";
    }

    @GetMapping("/")
    public String test() {
        return "OK";
    }
}
