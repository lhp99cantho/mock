package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.user.UserDAO;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RegisterController {

    @Autowired
    SessionService session;
    String urlPage = "user/";
    @Autowired
    UserDAO userDao;
    String code;
    String confirmType;
    String emailO;

    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        session.remove("user_temp2");
        return urlPage + "register";
    }

    @PostMapping(value ="/register")
    public String register(Model m, User user, @RequestParam("name") String name,
                           @RequestParam("userName") String userName, @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("email") String userEmail, @RequestParam("password") String password,
                           @RequestParam("confirmPassword") String confirmPass) {
        int loi = 0;
        user = new User(name, userName, phoneNumber, userEmail, password, false, false,"default.jpg");
        user = user.getId().getName;

        session.set("user_temp2", user);

        //Kiem tra username
        if (userName.isEmpty()) {
            m.addAttribute("messageU", "*Username không được bỏ trống!");
            loi++;
        } else {
            if (userDao.findById(Long.valueOf(userName)).isEmpty()) {
                m.addAttribute("messageU", "*Username đã tồn tại!");
                loi++;
            } else {
                m.addAttribute("messageU", null);
            }
        }

        //Kiem tra ho ten
        if (name.isEmpty()) {
            m.addAttribute("messageF", "*Họ tên không được bỏ trống!");
            loi++;
        } else {
            m.addAttribute("messageF", null);
        }
        String dinhDangSdt = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        //Kiem tra so dien thoai
        if (phoneNumber.isEmpty()) {
            m.addAttribute("messagePN", "*Số điện thoại không được bỏ trống!");
            loi++;
        } else {
            if (!phoneNumber.matches(dinhDangSdt)) {
                m.addAttribute("messagePN", "*Định dạng sdt không hợp lệ!");
                loi++;
            } else {
                m.addAttribute("messagePN", null);
            }
        }

        //kiem tra dinh dang email
        String dinhdangGmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@gmail+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String dinhdangFpt = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "fpt+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (userEmail.isEmpty()) {
            m.addAttribute("messageE", "*Email không được bỏ trống!");
            loi++;
        } else {
            if (!userEmail.matches(dinhdangGmail) && !userEmail.matches(dinhdangFpt)) {
                m.addAttribute("messageE", "*Định dạng email không hợp lệ!");
                loi++;
            } else {
                m.addAttribute("messageE", null);
                emailO= userEmail;
            }
        }

        //Kiem tra password
        if (password.isEmpty()) {
            m.addAttribute("messageP", "*Vui lòng nhập mật khẩu!");
            loi++;
        } else {
            if (password.length() < 3) {
                m.addAttribute("messageP", "*Mật khẩu mới phải có ít nhất 3 ký tự!");
                loi++;
            } else {
                m.addAttribute("messageP", null);
            }
        }

        //Kiem tra confirm password
        if (confirmPass.isEmpty()) {
            m.addAttribute("messageCP", "*Vui lòng nhập lại mật khẩu!");
            loi++;
        } else {
            if (!confirmPass.equals(password)) {
                m.addAttribute("messageCP", "*Mật khẩu nhập lại không trùng khớp!");
                loi++;
            } else {
                m.addAttribute("messageCP", null);
            }
        }

        if (loi == 0) {

            userDao.save(user);

            m.addAttribute("message", "Đăng ký tài khoản thành công! Vui lòng kiểm tra email của bạn để xác nhận "
                    + "và kích hoạt tài khoản!");

            session.remove("user_temp2");

            code = randomMa(6);
            mailer.queue(userEmail, "<UTOP> Xác nhận tài khoản của bạn",
                    getHTMLT());

            confirmType = "register";
        }

        return urlPage + "register";
    }
}
