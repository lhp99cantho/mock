package com.example.service01.Controller;

import com.example.service01.Service.UserService;
import com.example.service01.exception.NotFoundException;
import com.example.service01.model.Entity.User;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("/forgotPassword")
    public String showForgotPasswordForm(Model model) {
        User user = new User();
        model.addAttribute("user", model);
        return "forgot_password";
    }


    @PostMapping("/forgotPassword")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String siteURL = userService.getSiteURL(request);
            String resetPasswordLink = siteURL + "/resetPassword?token=" + token;
            userService.sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (NotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        return "forgot_password";
    }

    @GetMapping("/resetPassword")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.findByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
        return "reset_password";

    }

    @PostMapping("/resetPassword")
        public String processResetPassword(HttpServletRequest request, Model model) {
            String token = request.getParameter("token");
            String password = request.getParameter("password");

            User user = userService.findByResetPasswordToken(token);
            model.addAttribute("title", "Reset your password");

            if (user == null) {
                model.addAttribute("message", "Invalid Token");
                return "message";
            } else {
                userService.updatePassword(user, password);

                model.addAttribute("message", "You have successfully changed your password.");
            }

            return "message";
    }

}