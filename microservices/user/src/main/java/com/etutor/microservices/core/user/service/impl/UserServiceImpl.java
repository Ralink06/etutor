package com.etutor.microservices.core.user.service.impl;

import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.factory.UserFactory;
import com.etutor.microservices.core.user.model.entity.User;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import com.etutor.microservices.core.user.repository.UserRepository;
import com.etutor.microservices.core.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserServiceImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    //@Transactional
    public UserSnapshot createUser(CreateUserInput input) {
        User parent = userFactory.create(input);

        return userRepository.save(parent).toSnapshot();
    }
}
