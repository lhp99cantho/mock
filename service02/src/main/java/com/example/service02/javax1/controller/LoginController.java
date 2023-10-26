package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.user.UserDAO;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private String url;
    @Autowired
    SessionService session;
    String urlPage = "user/";
    @Autowired
    UserDAO userDao;

    @GetMapping(value = "/logout")
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
        session.remove("user");
        return "redirect:/UTOP/index.html";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        session.remove("user_temp");
        return urlPage + "login";
    }

    @PostMapping(value ="/login")
    public String login(Model m, @RequestParam("username") String username, @RequestParam("password") String pass) {
        User user = userDao.getById(Long.valueOf(username));
        int loi = 0;
        session.set("user_temp", username);

        // Check Login
        if (username.isEmpty()) {
            m.addAttribute("messageU", "*Vui lòng nhập tài khoản!");
            loi++;
        } else {
            m.addAttribute("messageU", null);
        }
        if (pass.isEmpty()) {
            m.addAttribute("messageP", "*Vui lòng nhập mật khẩu!");
            loi++;
        } else {
            m.addAttribute("messageP", null);
        }
        if (loi == 0) {
            if (user != null && user.isUserActive() == true) {
                if (user.getPassword().equals(pass)) {
                    m.addAttribute("message", "*Đăng nhập thành công!");
                    session.set("user", user);
                    session.remove("user_temp");
                    String uri = session.get("user", "/UTOP/index.html");
                    if (uri != null) {
                        System.out.println(uri);
                        return "redirect:" + uri;
                    } else {
                        return "redirect:/UTOP/index.html";
                    }
                } else {
                    m.addAttribute("messageP", "*Sai mật khẩu! :))");
                }
            } else if(user.isUserActive() == false) {
                m.addAttribute("messageU", "*Không thể đăng nhập bằng tài khoản này!");
            }else {
                m.addAttribute("messageU", "*Tài khoản này không tồn tại!");
            }
        }
        return urlPage + "login";
    }
}
