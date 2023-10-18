package com.example.service01.Controller;


import com.example.service01.Service.AuthService;
import com.example.service01.exception.ApplicationException;
import com.example.service01.model.request.LoginRequest;
import com.example.service01.model.request.RegisterRequest;
import com.example.service01.model.response.ApiResponse;
import com.example.service01.model.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse authResponse = authService.register(request);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(authResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}