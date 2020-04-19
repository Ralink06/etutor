package com.etutor.microservices.core.user.model.entity;


import com.etutor.api.user.Role;
import com.etutor.microservices.core.user.model.UserConstants;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(min = UserConstants.NAME_MIN_LENGTH, max = UserConstants.NAME_MAX_LENGTH)
    private String firstName;

    @NotBlank
    @Size(min = UserConstants.NAME_MIN_LENGTH, max = UserConstants.NAME_MAX_LENGTH)
    private String lastName;

    //    @NotNull TODO: password is required when role equals ADULT
    @Size(min = UserConstants.PASSWORD_MIN_LENGTH, max = UserConstants.PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    private Boolean active;

    //    @NotBlank TODO: email is required when role equals ADULT
    @Email
    @Size(max = UserConstants.EMAIL_MAX_LENGTH)
    private String email;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @DBRef
    @Builder.Default
    private List<User> children = new ArrayList<>();

    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public UserSnapshot toSnapshot() {
        List<UserSnapshot> children = this.children.stream()
                .map(User::toSnapshot)
                .collect(Collectors.toList());

        return UserSnapshot.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .password(this.password)
                .active(this.active)
                .email(this.email)
                .roles(this.roles)
                .children(children)
                .build();
    }
}