package com.example.service01.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendRegistrationEmail(String to) {
        String subject = "Sign Up Success";
        String text = "Your account has been successfully registered!";
        sendEmail(to, subject, text);
    }


}





