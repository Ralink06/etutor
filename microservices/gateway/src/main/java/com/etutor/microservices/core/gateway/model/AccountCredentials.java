package com.etutor.microservices.core.gateway.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCredentials {

    private String username;
    private String password;
}