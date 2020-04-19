package com.etutor.microservices.core.user.controller;

import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerPOSTcreateUser {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateNewUser() throws Exception {
        //given
        CreateUserInput createUserInput = CreateUserInput.builder()
                .firstName("Johny")
                .lastName("Bravo")
                .email("correct@email.com")
                .dateOfBirth(LocalDate.of(1995, 11, 15))
                .password("password")
                .build();

        //when
        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(createUserInput)))
                .andDo(print());

        //then
        result.andExpect(status().isOk());
        assertThat(userRepository.count()).isEqualTo(1);
    }
}
