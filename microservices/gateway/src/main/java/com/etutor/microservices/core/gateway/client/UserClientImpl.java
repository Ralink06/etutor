package com.etutor.microservices.core.gateway.client;

import com.etutor.microservices.core.gateway.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class UserClientImpl implements UserClient {

    private final RestTemplate restTemplate;

    public UserClientImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<User> getByUsername(final String username) {
        //TODO replace URL when USER APP will be available
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8081/user/" + username, User.class));
    }
}
