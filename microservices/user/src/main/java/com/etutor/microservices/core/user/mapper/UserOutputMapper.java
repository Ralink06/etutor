package com.etutor.microservices.core.user.mapper;

import com.etutor.api.user.output.ChildOutput;
import com.etutor.api.user.output.UserOutput;
import com.etutor.microservices.core.user.model.snapshot.UserSnapshot;
import java.util.List;
import java.util.stream.Collectors;

public class UserOutputMapper {
    
    public static UserOutput convert(final UserSnapshot userSnapshot) {
        List<ChildOutput> children = userSnapshot.getChildren().stream()
                .map(UserOutputMapper::convertChild)
                .collect(Collectors.toList());
        
        return UserOutput.builder()
                .id(userSnapshot.getId())
                .email(userSnapshot.getEmail())
                .firstName(userSnapshot.getFirstName())
                .lastName(userSnapshot.getLastName())
                .children(children)
                .dateOfBirth(userSnapshot.getDateOfBirth())
                .build();
    }

    private static ChildOutput convertChild(final UserSnapshot userSnapshot) {
        return ChildOutput.builder()
                .id(userSnapshot.getId())
                .firstName(userSnapshot.getFirstName())
                .lastName(userSnapshot.getLastName())
                .email(userSnapshot.getEmail())
                .dateOfBirth(userSnapshot.getDateOfBirth())
                .build();
    }
}
