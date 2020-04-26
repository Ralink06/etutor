package com.etutor.microservices.core.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.etutor.api.user.Role;
import com.etutor.microservices.core.user.model.entity.User;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shoulSaveNewUser() {
        //given
        User user = User.builder()
            .firstName("Rafał")
            .lastName("Koper")
            .dateOfBirth(LocalDate.of(1995, 11, 15))
            .active(true)
            .password("password")
            .email("rafal14kop@gmail.com")
            .roles(Collections.singleton(Role.ADULT))
            .build();

        //when
        UserSnapshot result = userRepository.save(user).toSnapshot();

        //then
        assertThat(result.getId()).isNotBlank();
    }
}
