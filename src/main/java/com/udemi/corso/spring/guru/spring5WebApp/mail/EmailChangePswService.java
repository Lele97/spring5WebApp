package com.udemi.corso.spring.guru.spring5WebApp.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
@AllArgsConstructor
@Log
@Qualifier("emailChangePswService")
public class EmailChangePswService implements EmailSender{

    JavaMailSender mailSender;

    @Override
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Change Your Password");
            helper.setFrom("hello@test.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.log(Level.SEVERE, "failed to send email" + e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
