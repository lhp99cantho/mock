package com.example.service01.Controller;

import com.example.service01.Service.AuthService;

import com.example.service01.exception.ApplicationException;
import com.example.service01.model.request.RegisterRequest;
import com.example.service01.model.response.AuthResponse;
import com.example.service01.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid RegisterRequest registerRequest, BindingResult bindingResult, Model model, HttpServletRequest request) {
        try {
            userValidator.validate(registerRequest, bindingResult);
            if (bindingResult.hasErrors()) {
                model.addAttribute("user", registerRequest);
                return "register";
            }
           AuthResponse authResponse = authService.register(registerRequest, authService.getSiteURL(request));
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

    @GetMapping({"/login","/"})
    public String getLoginPage() {return "login";}

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("user", registerRequest);
        return "register";
    }

}
