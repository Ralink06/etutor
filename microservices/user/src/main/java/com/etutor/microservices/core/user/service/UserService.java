package com.etutor.microservices.core.user.service;

import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.model.entity.ConfirmationToken;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;

public interface UserService {

    UserSnapshot createUser(CreateUserInput input);

    void confirmUser(ConfirmationToken token);
}
