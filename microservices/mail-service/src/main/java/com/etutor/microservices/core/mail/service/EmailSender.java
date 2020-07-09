package com.etutor.microservices.core.mail.service;

import com.etutor.microservices.core.mail.model.Mail;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class EmailSender {

    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    public EmailSender(EmailService emailService, TemplateEngine templateEngine) {
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    public void send(final Mail mail) {
        Context context = new Context();
        mail.getVariables().forEach(context::setVariable);

        String body = templateEngine.process(mail.getTemplate(), context);
        emailService.sendEmail(mail.getTo(), mail.getSubject(), body);
    }
}
