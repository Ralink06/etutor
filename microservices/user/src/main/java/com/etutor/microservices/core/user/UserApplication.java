package com.etutor.microservices.core.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        log.debug("Starting as a standalone user application");

        final SpringApplication app = new SpringApplication(UserApplication.class);

        app.run(args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate rest(final RestTemplateBuilder builder) {
        return builder.build();
    }
}
