package com.example.service01.Service;


import com.example.service01.model.request.LoginRequest;
import com.example.service01.model.request.RegisterRequest;
import com.example.service01.model.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);

}
