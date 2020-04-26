package com.etutor.microservices.core.gateway.client;

import com.etutor.microservices.core.gateway.model.User;
import java.util.Optional;

public interface UserClient {

    Optional<User> getByUsername(String username);
}
