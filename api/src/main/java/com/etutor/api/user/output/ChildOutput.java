package com.etutor.api.user.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class ChildOutput {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDate dateOfBirth;
}
