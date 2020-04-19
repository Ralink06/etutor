package com.etutor.api.user.input;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
