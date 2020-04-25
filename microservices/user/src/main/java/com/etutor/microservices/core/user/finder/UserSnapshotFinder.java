package com.etutor.microservices.core.user.finder;

import com.etutor.microservices.core.user.model.entity.User;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import com.etutor.microservices.core.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserSnapshotFinder {
    
    private UserRepository userRepository;

    public UserSnapshotFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Optional<UserSnapshot> findByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(User::toSnapshot);
    }
    
    public Optional<UserSnapshot> findById(String id) {
        return userRepository.findById(id)
                .map(User::toSnapshot);
    }
}
