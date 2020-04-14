package com.etutor.api.user.input;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChildInput {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
