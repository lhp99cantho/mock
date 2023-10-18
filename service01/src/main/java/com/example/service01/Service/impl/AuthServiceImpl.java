package com.example.service01.Service.impl;


import com.example.service01.Security.JwtToken;
import com.example.service01.Security.UserSecurity;
import com.example.service01.Service.AuthService;
import com.example.service01.Service.UserService;
import com.example.service01.model.Entity.User;
import com.example.service01.model.request.LoginRequest;
import com.example.service01.model.request.RegisterRequest;
import com.example.service01.model.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userService.findByUsername(request.getUsername());
        if (user != null) {
            UserSecurity userSecurity = new UserSecurity(user);

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("username", user.getUsername());
            extraClaims.put("authorities", userSecurity.getAuthorities());
            String accessToken = jwtToken.generateToken(extraClaims, userSecurity);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setUsername(request.getUsername());
            authResponse.setAccessToken(accessToken);
            return authResponse;
        }

        return null;
    }



    @Override
    public AuthResponse register(RegisterRequest request) {
        // new User
        User user = new User();
        user.setFullname(request.getFullname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("GUEST");
        user.setStatus(true);

        // Save
        userService.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(request.getUsername());

        return authResponse;
    }
}