package com.etutor.api.user.input;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserInput {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
}
