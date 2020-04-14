package com.etutor.microservices.core.gateway.client;

import com.etutor.api.user.output.auth.UserAuthOutput;
import com.etutor.microservices.core.gateway.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

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
        //TODO replace URL when USER APP will be available
        return Optional.ofNullable(restTemplate.getForObject(userServiceUrl + "/user/auth/" + username, User.class));
    }
}
