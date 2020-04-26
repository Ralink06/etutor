package com.etutor.microservices.core.user.mapper;

import com.etutor.api.user.output.auth.UserAuthOutput;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import java.util.List;
import java.util.stream.Collectors;

public class UserAuthOutputMapper {

    public static UserAuthOutput convert(final UserSnapshot userSnapshot) {
        List<UserAuthOutput> children = userSnapshot.getChildren().stream()
                .map(UserAuthOutputMapper::convertChild)
                .collect(Collectors.toList());

        return UserAuthOutput.builder()
                .id(userSnapshot.getId())
                .email(userSnapshot.getEmail())
                .firstName(userSnapshot.getFirstName())
                .lastName(userSnapshot.getLastName())
                .children(children)
                .dateOfBirth(userSnapshot.getDateOfBirth())
                .roles(userSnapshot.getRoles())
                .active(userSnapshot.isActive())
                .password(userSnapshot.getPassword())
                .build();
    }


    private static UserAuthOutput convertChild(final UserSnapshot userSnapshot) {
        return UserAuthOutput.builder()
                .id(userSnapshot.getId())
                .active(userSnapshot.isActive())
                .firstName(userSnapshot.getFirstName())
                .lastName(userSnapshot.getLastName())
                .email(userSnapshot.getEmail())
                .dateOfBirth(userSnapshot.getDateOfBirth())
                .roles(userSnapshot.getRoles())
                .password(userSnapshot.getPassword())
                .build();
    }
}
