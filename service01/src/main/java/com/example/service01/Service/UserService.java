package com.example.service01.Service;


import com.example.service01.exception.NotFoundException;
import com.example.service01.model.Entity.User;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface UserService {

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void updateResetPasswordToken(String token, String email) throws NotFoundException;

    User findByResetPasswordToken(String token);

    void  updatePassword(User user, String newPassword);

    String getSiteURL(HttpServletRequest request);

    void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException;
}
