package com.etutor.microservices.core.mail.listener.user;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface UserSource {

    String INPUT = "user-input";

    @Input(INPUT)
    MessageChannel output();
}
