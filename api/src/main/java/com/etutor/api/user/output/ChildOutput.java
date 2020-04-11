package com.etutor.api.user.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
class ChildOutput {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
}
