package com.etutor.microservices.core.user.factory;

import com.etutor.api.user.Role;
import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.model.entity.User;
import java.util.Collections;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create(final CreateUserInput input) {
        String password = passwordEncoder.encode(input.getPassword());

        return User.builder()
            .firstName(input.getFirstName())
            .lastName(input.getLastName())
            .email(input.getEmail())
            .password(password)
            .active(true)
            .roles(Collections.singleton(Role.ADULT))
            .dateOfBirth(input.getDateOfBirth())
            .build();
    }
}
