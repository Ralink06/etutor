package com.etutor.api.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Role {
    ADULT(Authority.GET_MY_ACCOUNT, Authority.GET_CHILD_ACCOUNT),

    CHILD(Authority.GET_MY_ACCOUNT);

    Role(final Authority... authorities) {
        this.authorities = Arrays.asList(authorities);
    }

    private final List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }
}
