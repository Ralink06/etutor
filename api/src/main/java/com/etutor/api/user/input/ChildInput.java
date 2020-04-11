package com.etutor.api.user.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChildInput {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
