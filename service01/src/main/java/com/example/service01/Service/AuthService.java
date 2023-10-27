package com.example.service01.Service;


import com.example.service01.model.Entity.User;
import com.example.service01.model.request.LoginRequest;
import com.example.service01.model.request.RegisterRequest;
import com.example.service01.model.response.AuthResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request, String siteURL) throws MessagingException, UnsupportedEncodingException;

    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;

    String getSiteURL(HttpServletRequest request);

    User findByVerificationCode(String code);

    public boolean verify(String verificationCode);






}
