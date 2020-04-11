package com.etutor.api.user.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateUserInput {


    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private List<ChildInput> children;
}
