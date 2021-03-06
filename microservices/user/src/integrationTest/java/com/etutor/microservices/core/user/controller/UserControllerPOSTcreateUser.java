package com.etutor.microservices.core.user.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.etutor.api.user.input.CreateUserInput;
import com.etutor.microservices.core.user.repository.ConfirmationTokenRepository;
import com.etutor.microservices.core.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class UserControllerPOSTcreateUser {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

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
