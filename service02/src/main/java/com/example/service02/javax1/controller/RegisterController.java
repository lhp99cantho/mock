package com.example.service02.javax1.controller;

import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.UserService;
import com.example.service02.javax1.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    private UserValidator userValidator;

    @Autowired
    UserService userService;

    @GetMapping(value = "/register")
    public String showPage(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        return  "register";
    }

    @PostMapping(value ="/register")
    public String submit(Model model, @ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        userValidator.validate(user,bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }

        userService.register(user);
        return "redirect:/product";
    }
}
