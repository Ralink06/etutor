package com.etutor.api.user.output.auth;

import com.etutor.api.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class UserAuthOutput {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final boolean active;
    private final String email;
    private final LocalDate dateOfBirth;
    private final List<UserAuthOutput> children;
    private final Set<Role> roles;
}
