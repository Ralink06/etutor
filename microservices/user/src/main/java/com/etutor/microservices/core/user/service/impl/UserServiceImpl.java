package com.etutor.microservices.core.user.service.impl;

import com.etutor.api.Event;
import com.etutor.api.Event.Type;
import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.event.EventSource;
import com.etutor.microservices.core.user.factory.UserFactory;
import com.etutor.microservices.core.user.mapper.UserOutputMapper;
import com.etutor.microservices.core.user.model.entity.User;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import com.etutor.microservices.core.user.repository.UserRepository;
import com.etutor.microservices.core.user.service.UserService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(EventSource.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final EventSource eventSource;

    public UserServiceImpl(UserRepository userRepository,
        UserFactory userFactory,
        EventSource eventSource) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.eventSource = eventSource;
    }

    @Override
    //@Transactional
    public UserSnapshot createUser(CreateUserInput input) {
        User parent = userFactory.create(input);

        UserSnapshot userSnapshot = userRepository.save(parent).toSnapshot();

        Event userCreatedEvent = new Event<>(Type.CREATED,
            userSnapshot.getId(), UserOutputMapper.convert(userSnapshot));

        eventSource.output().send(MessageBuilder.withPayload(userCreatedEvent).build());
        return userSnapshot;
    }
}
