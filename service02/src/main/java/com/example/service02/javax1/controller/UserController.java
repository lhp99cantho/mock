package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.user.UserDAO;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.MailerService;
import com.example.service02.javax1.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("UTOP")
public class UserController {

    @Autowired
    UserDAO userDao;

    @Autowired
    SessionService session;

    @Autowired
    MailerService mailer;
    private String url;

    String urlPage = "user/";
    String code;
    private String message;
    String user, emailO;


    // Forgot Password page
    @RequestMapping("/forgotPassword")
    public String forgotPassPage() {
        return urlPage + "forgotPassword";
    }



    @PostMapping("/forgotPass")
    public String sendPass(Model m, @RequestParam("userUsername") String userUsername, @RequestParam("userEmail") String userEmail)
            throws MessagingException {
        int loi = 0;
        session.set("user_temp", userUsername);
        session.set("email_temp", userEmail);
        if (userUsername.isEmpty()) {
            m.addAttribute("messageU", "*Vui lòng nhập tài khoản của bạn!");
            loi++;
        } else {
            Optional<User> findByID = userDao.findById(Long.valueOf(userUsername));
            user = userUsername;
            if (findByID.isEmpty()) {
                m.addAttribute("messageU", "*Tài khoản này không tồn tại!");
                loi++;
            } else {
                m.addAttribute("messageU", null);
            }
        }

        String dinhdangGmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@gmail+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String dinhdangFpt = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "fpt+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (userEmail.isEmpty()) {
            m.addAttribute("messageE", "*Email không được bỏ trống!");
            loi++;
        } else {
            if (!userEmail.matches(dinhdangGmail) && ! userEmail.matches(dinhdangFpt)) {
                m.addAttribute("messageE", "*Định dạng email không hợp lệ!");
                loi++;
            } else {
                m.addAttribute("messageE", null);
                emailO = userEmail;
            }
        }

        if (loi == 0) {

            session.remove("user_temp");
            session.remove("email_temp");
            code = randomMa(6);

            mailer.queue(userEmail, "<UTOP> Xác nhận tài khoản của bạn",
                    getHTMLT());
            confirmType = "forgot";

            m.addAttribute("message", "Đang gửi mã xác nhận đến email của bạn!");
        }

        return urlPage + "forgotPass";
    }

    // Change Password page
    @RequestMapping("/changePass")
    public String changePassPage() {
        return urlPage + "changePass";
    }

    @PostMapping("/changePass")
    public String changePass(Model m, User user, @RequestParam("oldPass") String oldPass,
                             @RequestParam("userPassword") String userPassword, @RequestParam("confirmPass") String confirmPass) {
        user = (User) session.get("user", "");
        int loi = 0;
        if (oldPass.isEmpty()) {
            m.addAttribute("messageOP", "*Vui lòng nhập mật khẩu cũ!");
            loi++;
        } else {
            if (!user.getPassword().equals(oldPass)) {
                m.addAttribute("messageOP", "*Vui lòng nhập đúng mật khẩu cũ!");
                loi++;
            } else {
                m.addAttribute("messageOP", null);
            }
        }
        if (userPassword.isEmpty()) {
            m.addAttribute("messageNP", "*Vui lòng nhập mật khẩu mới!");
            loi++;
        } else {
            if (userPassword.length() < 3) {
                m.addAttribute("messageNP", "*Mật khẩu mới phải có ít nhất 3 ký tự!");
                loi++;
            } else {
                m.addAttribute("messageNP", null);
            }
        }
        if (confirmPass.isEmpty()) {
            m.addAttribute("messageCP", "*Vui lòng nhập lại mật khẩu!");
            loi++;
        } else {
            if (!confirmPass.equals(userPassword)) {
                m.addAttribute("messageCP", "*Mật khẩu nhập lại không trùng khớp!");
                loi++;
            } else {
                m.addAttribute("messageCP", null);
            }
        }
        if (loi == 0) {
            user.setPassword(userPassword);
            userDao.save(user);
            m.addAttribute("message", "*Đổi mật khẩu thành công!!!");
        }

        return urlPage + "changePass";
    }

    // ConfirmMessage page
    @RequestMapping("/confirmMessage")
    public String confirmMessagePage() {
        return urlPage + "confirmMessage";
    }

    @PostMapping("/confirmMessage")
    public String confirmMessage(Model m, @RequestParam("code") String codeE, Long userID) {
        int loi = 0;
        System.out.println(code);
        if (codeE.isEmpty()) {
            m.addAttribute("messageC", "*Không được bỏ trống!");
            loi++;
        } else {
            if (!codeE.equals(code)) {
                m.addAttribute("messageC", "*Mã xác nhận không đúng!");
                loi++;
            } else {
                m.addAttribute("messageC", null);
            }
        }
        if (loi == 0) {
            if (confirmType.equalsIgnoreCase("forgot")) {
                mailer.queue(emailO, "<UTOP>Mật khẩu tài khoản " + user,
                        getHTMLT());
            } else {
                User user = userDao.findById(userID).get();
                user.setUserActive(true);
                userDao.save(user);

                mailer.queue(emailO, "<UTOP> Đã kích hoạt tài khoản " + user,
                        getHTMLT());
            }

            m.addAttribute("message", "Xác nhận tài khoản thành công!");
        }

        return urlPage + "confirmMessage";
    }



    // Extends Methods - Random code
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = digits;
    private static Random generator = new Random();

    public String randomMa(int soKyTu) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < soKyTu; i++) {
            int number = randomNumber (0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public String getHTMLT() {
        this.code = code;
        this.message = message;
        this.url = url;
        return this.getHTMLT();
    }


}
