package com.example.service02.javax1.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
    String from;
    String to;
    String[] cc;
    String[] bcc;
    String subject;
    String body;
    List<File> attachments;

    public MailInfo(String to, String subject, String body) {
        this.from = "UTOP <UTOP@fpt.com.vn>";
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
