package com.etutor.microservices.core.gateway.listener;

import com.etutor.microservices.core.gateway.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        User authenticatedUser = (User) event.getAuthentication().getPrincipal();

        log.info("Authentication success for user <{}>", authenticatedUser.getId());
    }
}
