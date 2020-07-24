package com.etutor.microservices.core.user.model.entity;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    private String id;

    @NotBlank
    private String token;

    @DBRef
    private User user;
}
