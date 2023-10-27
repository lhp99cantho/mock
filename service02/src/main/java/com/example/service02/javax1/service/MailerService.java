package com.example.service02.javax1.service;

import com.example.service02.javax1.constant.MailInfo;
import org.springframework.messaging.MessagingException;

public interface MailerService {
    void send(MailInfo mail) throws MessagingException, jakarta.mail.MessagingException;

    void send(String to, String subject, String body) throws MessagingException, jakarta.mail.MessagingException;

    void queue(MailInfo mail);

    void queue(String to, String subject, String body);
}
