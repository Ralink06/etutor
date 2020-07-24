package com.etutor.microservices.core.user.mapper;

import com.etutor.api.user.output.UserOutput;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;

public class UserOutputMapper {

    public static UserOutput convert(final UserSnapshot userSnapshot) {

        return UserOutput.builder()
            .id(userSnapshot.getId())
            .email(userSnapshot.getEmail())
            .firstName(userSnapshot.getFirstName())
            .lastName(userSnapshot.getLastName())
            .dateOfBirth(userSnapshot.getDateOfBirth())
            .build();
    }
}
