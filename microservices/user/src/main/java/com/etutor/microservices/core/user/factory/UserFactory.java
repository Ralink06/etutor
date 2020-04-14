package com.etutor.microservices.core.user.factory;

import com.etutor.api.user.Role;
import com.etutor.api.user.input.ChildInput;
import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create(final CreateUserInput input, List<User> children) {
        String password = passwordEncoder.encode(input.getPassword());

        return User.builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .password(password)
                .active(true)
                .roles(Collections.singleton(Role.ADULT))
                .dateOfBirth(input.getDateOfBirth())
                .children(children)
                .build();
    }

    public User createChild(final ChildInput input) {
        return createUserFromChild(input);
    }

    private User createUserFromChild(ChildInput childInput) {
        return User.builder()
                .firstName(childInput.getFirstName())
                .lastName(childInput.getLastName())
                .dateOfBirth(childInput.getDateOfBirth())
                .active(false)
                .roles(Collections.singleton(Role.CHILD))
                .build();
    }
}