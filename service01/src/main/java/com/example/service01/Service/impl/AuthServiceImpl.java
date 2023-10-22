package com.example.service01.Service.impl;


import com.example.service01.Security.JwtToken;
import com.example.service01.Security.UserSecurity;
import com.example.service01.Service.AuthService;
import com.example.service01.Service.UserService;
import com.example.service01.exception.ApplicationException;
import com.example.service01.model.Entity.User;
import com.example.service01.model.request.LoginRequest;
import com.example.service01.model.request.RegisterRequest;
import com.example.service01.model.response.AuthResponse;
import com.example.service01.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.util.HashMap;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSenderImpl mailSender;


    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userService.findByUsername(request.getUsername());
        if (user != null) {
            UserSecurity userSecurity = new UserSecurity(user);

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("username", user.getUsername());
            extraClaims.put("authorities", userSecurity.getAuthorities());
            String accessToken = jwtToken.generateToken(extraClaims, userSecurity);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setUsername(request.getUsername());
            authResponse.setAccessToken(accessToken);
            return authResponse;
        }

        return null;
    }


    @Override
    public AuthResponse register(RegisterRequest request, String siteURL) throws MessagingException {
        // new User
        User user = new User();
        user.setFullname(request.getFullname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        String randomCode = RandomString.make(6);
        user.setVerificationCode(randomCode);
        user.setRole("GUEST");
        user.setStatus(false);

        // Save
        userService.save(user);
        sendVerificationEmail(user, siteURL);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(request.getUsername());
        return authResponse;
    }

    @Override
    public void sendVerificationEmail(User user, String siteURL) {

        try {
            String toAddress = user.getEmail();
            String subject = "Please verify your registration";
            String content = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Thank you,<br>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            content = content.replace("[[name]]", user.getFullname());
            String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
            content = content.replace("[[URL]]", verifyURL);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException();
        }
    }
@Override
public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @Override
    public User findByVerificationCode(String code) {
        return userRepository.findByVerificationCode(code).orElse(null);
    }

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode).orElse(null);

        if (user == null || user.isStatus()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setStatus(true);
            userRepository.save(user);
            return true;
        }
    }

}