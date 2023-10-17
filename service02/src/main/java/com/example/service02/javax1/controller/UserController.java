package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.user.UserDAO;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.MailerService;
import com.example.service02.javax1.service.SessionService;
import jakarta.validation.constraints.*;
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

    String urlPage = "user/";
    String code;
    private String message;
    private String url;
    String confirmType;


    // Login page :)))
    @RequestMapping("/login")
    public String loginPage(Model m) {
        session.remove("user_temp");
        return urlPage + "login";
    }

    @PostMapping ("/login")
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
                if (user.getUserPassword().equals(pass)) {
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

    @RequestMapping("/logout")
    public String logout() {
        session.remove("user");
        return "redirect:/UTOP/index.html";
    }

    // Register page
    @RequestMapping("/register")
    public String registerPage() {
        session.remove("user_temp2");
        return urlPage + "register";
    }

    @PostMapping("/register")
    public String register(Model m, User user, @RequestParam("userUsername") String userUsername,
                           @RequestParam("userName") String userName, @RequestParam("userPhoneNumber") String userPhoneNumber,
                           @RequestParam("userEmail") String userEmail, @RequestParam("userPassword") String userPassword,
                           @RequestParam("confirmPassword") String confirmPass) {
        int loi = 0;
        user = new User(userUsername, userPassword, userName, userEmail, userPhoneNumber, false, false,"default.jpg");
        user = user.getUserID().getUserName();

        session.set("user_temp2", user);

        //Kiem tra username
        if (userUsername.isEmpty()) {
            m.addAttribute("messageU", "*Username không được bỏ trống!");
            loi++;
        } else {
            if (userDao.findById(Long.valueOf(userUsername)).isEmpty()) {
                m.addAttribute("messageU", "*Username đã tồn tại!");
                loi++;
            } else {
                m.addAttribute("messageU", null);
            }
        }

        //Kiem tra ho ten
        if (userName.isEmpty()) {
            m.addAttribute("messageF", "*Họ tên không được bỏ trống!");
            loi++;
        } else {
            m.addAttribute("messageF", null);
        }
        String dinhDangSdt = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        //Kiem tra so dien thoai
        if (userPhoneNumber.isEmpty()) {
            m.addAttribute("messagePN", "*Số điện thoại không được bỏ trống!");
            loi++;
        } else {
            if (!userPhoneNumber.matches(dinhDangSdt)) {
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
                emailO = userEmail;
            }
        }

        //Kiem tra password
        if (userPassword.isEmpty()) {
            m.addAttribute("messageP", "*Vui lòng nhập mật khẩu!");
            loi++;
        } else {
            if (userPassword.length() < 3) {
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
            if (!confirmPass.equals(userPassword)) {
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

    // Forgot Password page
    @RequestMapping("/forgotPassword")
    public String forgotPassPage() {
        return urlPage + "forgotPassword";
    }

    String user, emailO;

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
            if (!user.getUserPassword().equals(oldPass)) {
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
            user.setUserPassword(userPassword);
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

    // Update Profile page
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
            user.setUserName(userName);
            user.setUserPhoneNumber(userPhoneNumber);
            user.setUserEmail(userEmail);

            userDao.save(user);
            m.addAttribute("message", "Cập nhật thành công!");
        }

        return urlPage + "updateProfile";
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
