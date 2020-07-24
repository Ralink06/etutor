package com.etutor.microservices.core.user.factory;

import com.etutor.microservices.core.user.model.entity.ConfirmationToken;
import com.etutor.microservices.core.user.model.entity.User;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationTokenFactory {

    public ConfirmationToken create(User user) {
        String token = UUID.randomUUID().toString();

        return ConfirmationToken.builder()
            .token(token)
            .user(user)
            .build();
    }
}
