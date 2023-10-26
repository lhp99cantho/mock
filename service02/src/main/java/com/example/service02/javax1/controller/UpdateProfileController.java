package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.user.UserDAO;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.SessionService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UpdateProfileController {
    private String url;
    @Autowired
    SessionService session;
    String urlPage = "user/";
    @Autowired
    UserDAO userDao;
    @RequestMapping("/updateProfile")
    public String updateProfile() {
        return urlPage + "updateProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfileUpdate(Model m, User user, @RequestParam("userName") @NotEmpty @NotBlank User userName,
                                      @RequestParam("userPhoneNumber") String userPhoneNumber, @RequestParam("userEmail") String userEmail) {
        int loi = 0;

        if (userName.isUserActive()) {
            m.addAttribute("messageF", "*Họ tên không được bỏ trống!");
            loi++;
        } else {
            m.addAttribute("messageF", null);
        }
        String dinhDangSdt = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if (userPhoneNumber.isEmpty()) {
            m.addAttribute("messageP", "*Số điện thoại không được bỏ trống!");
            loi++;
        } else {
            if (!userPhoneNumber.matches(dinhDangSdt)) {
                m.addAttribute("messageP", "*Định dạng sdt không hợp lệ!");
                loi++;
            } else {
                m.addAttribute("messageP", null);
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
            }
        }
        if (loi == 0) {
            user = session.get("user", "");
            user.setPhoneNumber(userPhoneNumber);
            user.setEmail(userEmail);

            userDao.save(user);
            m.addAttribute("message", "Cập nhật thành công!");
        }

        return urlPage + "updateProfile";
    }
}
