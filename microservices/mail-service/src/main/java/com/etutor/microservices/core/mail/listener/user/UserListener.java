package com.etutor.microservices.core.mail.listener.user;


import com.etutor.api.user.event.UserCreatedEvent;
import com.etutor.microservices.core.mail.model.Mail;
import com.etutor.microservices.core.mail.service.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding(UserSource.class)
public class UserListener {

    private final EmailSender emailSender;
    private final String confirmationAddress;

    public UserListener(EmailSender emailSender,
        @Value("${user.address}") String confirmationAddress) {
        this.emailSender = emailSender;
        this.confirmationAddress = confirmationAddress;
    }

    @StreamListener(target = UserSource.INPUT)
    public void process(UserCreatedEvent event) {
        Mail mail =
            new Mail(event.getEmail(), "Welcome in eTutor", "welcome");

        mail.getVariables()
            .put("serverAddress", confirmationAddress);
        mail.getVariables()
            .put("token", event.getRegistrationToken());
        mail.getVariables().put("firstName", event.getFirstName());
        mail.getVariables().put("lastName", event.getLastName());

        emailSender.send(mail);
    }
}
