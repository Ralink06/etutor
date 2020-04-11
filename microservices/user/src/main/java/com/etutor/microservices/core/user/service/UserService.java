package com.etutor.microservices.core.user.service;

import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;

import java.util.Optional;

public interface UserService {

    UserSnapshot createUser(CreateUserInput input);

    Optional<UserSnapshot> getByUsername(String username);
}
