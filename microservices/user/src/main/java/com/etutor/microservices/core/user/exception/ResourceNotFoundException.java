package com.etutor.microservices.core.user.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Resource not found";

    public ResourceNotFoundException() {
        super(MESSAGE);
    }
}
