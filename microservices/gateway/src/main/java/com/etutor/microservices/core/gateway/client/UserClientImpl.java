package com.etutor.microservices.core.gateway.client;

import com.etutor.microservices.core.gateway.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClientImpl implements UserClient {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public UserClientImpl(final RestTemplate restTemplate,
        @Value("${microservices.user.url}") final String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    @Override
    public Optional<User> getByUsername(final String username) {
        return Optional
                .ofNullable(restTemplate.getForObject(userServiceUrl + "/user/auth/" + username, User.class));
    }
}
