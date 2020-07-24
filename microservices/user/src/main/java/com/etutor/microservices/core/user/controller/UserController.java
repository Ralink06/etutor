package com.etutor.microservices.core.user.controller;

import com.etutor.api.user.input.CreateUserInput;
import com.etutor.api.user.output.UserOutput;
import com.etutor.api.user.output.auth.UserAuthOutput;
import com.etutor.microservices.core.user.exception.ResourceNotFoundException;
import com.etutor.microservices.core.user.finder.UserSnapshotFinder;
import com.etutor.microservices.core.user.mapper.UserAuthOutputMapper;
import com.etutor.microservices.core.user.mapper.UserOutputMapper;
import com.etutor.microservices.core.user.model.entity.ConfirmationToken;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import com.etutor.microservices.core.user.repository.ConfirmationTokenRepository;
import com.etutor.microservices.core.user.service.UserService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserSnapshotFinder userSnapshotFinder;
    private ConfirmationTokenRepository confirmationTokenRepository;

    public UserController(UserService userService, UserSnapshotFinder userSnapshotFinder,
        ConfirmationTokenRepository confirmationTokenRepository) {
        this.userService = userService;
        this.userSnapshotFinder = userSnapshotFinder;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @PostMapping
    public ResponseEntity<UserOutput> createUser(
        @RequestBody final CreateUserInput createUserInput) {
        UserSnapshot userSnapshot = userService.createUser(createUserInput);

        return ResponseEntity.ok(UserOutputMapper.convert(userSnapshot));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserOutput> findById(@PathVariable final String userId) {
        return userSnapshotFinder.findById(userId)
            .map(UserOutputMapper::convert)
            .map(ResponseEntity::ok)
            .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/me")
    public ResponseEntity<UserOutput> findByHeaderUserId(
        @RequestHeader("authentication-userid") final String userId) {
        return userSnapshotFinder.findById(userId)
            .map(UserOutputMapper::convert)
            .map(ResponseEntity::ok)
            .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/auth/{username}")
    public ResponseEntity<UserAuthOutput> findAuthInfoByUsername(@PathVariable String username) {
        return userSnapshotFinder.findByUsername(username)
            .map(UserAuthOutputMapper::convert)
            .map(ResponseEntity::ok)
            .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/confirmation")
    public ResponseEntity confirmation(@RequestParam String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository
            .findByToken(token);

        if (confirmationToken.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        this.userService.confirmUser(confirmationToken.get());

        return ResponseEntity.ok().build();
    }
}
