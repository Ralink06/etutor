package com.etutor.microservices.core.mail.listener.user;


import com.etutor.api.Event;
import com.etutor.api.user.output.UserOutput;
import com.etutor.microservices.core.mail.model.Mail;
import com.etutor.microservices.core.mail.service.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding(EventSource.class)
public class UserListener {

    private final EmailSender emailSender;

    public UserListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @StreamListener(target = EventSource.INPUT)
    public void process(Event<String, UserOutput> event) {
        UserOutput userOutput = event.getData();

        Mail mail =
            new Mail(userOutput.getEmail(), "Welcome in eTutor", "welcome");

        mail.getVariables().put("firstName", userOutput.getFirstName());
        mail.getVariables().put("lastName", userOutput.getLastName());

        emailSender.send(mail);
    }
}
