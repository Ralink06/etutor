package com.etutor.microservices.core.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to,
        String title,
        String body) {

        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setFrom("SportEventApplication");
            helper.setSubject(title);
            helper.setText(body, true);

        } catch (MessagingException e) {
            log.error("Error sending email to <{}>", to, e);
        }

        log.info("Sending email to <{}> with subject <{}>", to, title);

        javaMailSender.send(mail);
    }
}
