package com.etutor.microservices.core.gateway.listener;

import com.etutor.microservices.core.gateway.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFailureListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();

        log.info("Authentication failure for user <{}>", user.getId());
    }
}
