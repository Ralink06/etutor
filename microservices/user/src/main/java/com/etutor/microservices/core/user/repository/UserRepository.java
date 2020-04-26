package com.etutor.microservices.core.user.repository;

import com.etutor.microservices.core.user.model.entity.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}
