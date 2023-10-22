package com.example.service01.Controller;

import com.example.service01.Service.AuthService;
import com.example.service01.exception.ApplicationException;
import com.example.service01.model.request.LoginRequest;
import com.example.service01.model.response.ApiResponse;
import com.example.service01.model.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse authResponse = authService.login(request);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(authResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException();
        }
    }

}

//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request, String siteURL) {
//        try {
//            AuthResponse authResponse = authService.register(request, siteURL);
//
//            if (authResponse != null) {
//                // Gửi email thông báo đăng ký thành công
//                User user = userService.findByUsername(request.getUsername());
//                emailService.sendRegistrationEmail(user.getEmail());
//            }
//
//            ApiResponse apiResponse = new ApiResponse();
//            apiResponse.ok(authResponse);
//            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        } catch (Exception ex) {
//            throw new ApplicationException();
//        }
//    }

