package com.etutor.microservices.core.user.service.impl;

import com.etutor.api.Event;
import com.etutor.api.user.event.UserCreatedEvent;
import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.clock.TimeService;
import com.etutor.microservices.core.user.event.EventSource;
import com.etutor.microservices.core.user.factory.ConfirmationTokenFactory;
import com.etutor.microservices.core.user.factory.UserFactory;
import com.etutor.microservices.core.user.model.entity.ConfirmationToken;
import com.etutor.microservices.core.user.model.entity.User;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import com.etutor.microservices.core.user.repository.ConfirmationTokenRepository;
import com.etutor.microservices.core.user.repository.UserRepository;
import com.etutor.microservices.core.user.service.UserService;
import java.util.Optional;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(EventSource.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationTokenFactory tokenFactory;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserFactory userFactory;
    private final EventSource eventSource;
    private final TimeService timeService;

    public UserServiceImpl(UserRepository userRepository,
        ConfirmationTokenFactory tokenFactory,
        ConfirmationTokenRepository confirmationTokenRepository,
        UserFactory userFactory,
        EventSource eventSource,
        TimeService timeService) {
        this.userRepository = userRepository;
        this.tokenFactory = tokenFactory;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userFactory = userFactory;
        this.eventSource = eventSource;
        this.timeService = timeService;
    }

    @Override
    //@Transactional
    public UserSnapshot createUser(CreateUserInput input) {
        User parent = userFactory.create(input);

        UserSnapshot userSnapshot = userRepository.save(parent).toSnapshot();

        ConfirmationToken confirmationToken = tokenFactory.create(parent);
        confirmationTokenRepository.save(confirmationToken);

        //event should be outside transaction later when @Transactional will be fixed
        Event userCreatedEvent = new UserCreatedEvent(userSnapshot.getId(),
            timeService.currentDate(),
            confirmationToken.getToken(),
            userSnapshot.getFirstName(),
            userSnapshot.getLastName(),
            userSnapshot.getEmail());

        eventSource.output().send(MessageBuilder.withPayload(userCreatedEvent).build());

        return userSnapshot;
    }

    @Override
    //@Transactional
    public void confirmUser(ConfirmationToken token) {
        User user = token.getUser();

        user.activate();

        userRepository.save(user);
    }
}
