package com.example.service01.Controller;

import com.example.service01.Service.AuthService;
import com.example.service01.Service.EmailService;
import com.example.service01.Service.UserService;
import com.example.service01.exception.ApplicationException;
import com.example.service01.model.Entity.User;
import com.example.service01.model.request.RegisterRequest;
import com.example.service01.model.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
        try {
            AuthResponse authResponse = authService.register(registerRequest, authService.getSiteURL(request));

            if (authResponse != null) {
                // Gửi email thông báo đăng ký thành công
                User user = userService.findByUsername(registerRequest.getUsername());
                emailService.sendRegistrationEmail(user.getEmail());
            }
            return "process_register";
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException();
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) {
        if (authService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/verify_success")
    public String verifyUser() {
        return "verify_success";
    }

    @GetMapping("/process_register")
    public String processRegister() {
        return "process_register";
    }


}
