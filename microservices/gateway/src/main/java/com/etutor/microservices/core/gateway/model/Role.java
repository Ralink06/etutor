package com.etutor.microservices.core.gateway.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Role {
    SYSTEM_ADMIN(Authority.GET_ALL_ACCOUNTS),

    USER(Authority.GET_MY_ACCOUNT);

    Role(final Authority... authorities) {
        this.authorities = Arrays.asList(authorities);
    }

    private final List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }
}
