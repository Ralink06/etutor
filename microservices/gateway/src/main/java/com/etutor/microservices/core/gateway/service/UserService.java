package com.etutor.microservices.core.gateway.service;

import com.etutor.microservices.core.gateway.client.UserClientImpl;
import com.etutor.microservices.core.gateway.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userServiceCustomImplementation")
public class UserService implements UserDetailsService {

    private final UserClientImpl userClientImpl;

    public UserService(final UserClientImpl userClientImpl) {
        this.userClientImpl = userClientImpl;
    }

    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userClientImpl.getByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}
