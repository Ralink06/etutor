package com.etutor.api.user.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserOutput {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDate dateOfBirth;
    private final List<ChildOutput> children;
}
