package com.etutor.api.user.output;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOutput {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
}
