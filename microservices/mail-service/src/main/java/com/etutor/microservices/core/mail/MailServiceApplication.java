package com.etutor.microservices.core.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MailServiceApplication {

    public static void main(String[] args) {
        log.debug("Starting as a standalone user application");

        final SpringApplication app = new SpringApplication(MailServiceApplication.class);

        app.run(args);
    }

}
