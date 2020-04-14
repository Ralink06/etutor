package com.etutor.microservices.core.user.service.impl;

import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.factory.UserFactory;
import com.etutor.microservices.core.user.model.entity.User;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import com.etutor.microservices.core.user.repository.UserRepository;
import com.etutor.microservices.core.user.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserServiceImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
//    @Transactional
    public UserSnapshot createUser(CreateUserInput input) {
        List<User> children = userRepository.saveAll(input.getChildren().stream()
                .map(userFactory::createChild)
                .collect(Collectors.toList()));

        User parent = userFactory.create(input, children);

        return userRepository.save(parent).toSnapshot();
    }
}
