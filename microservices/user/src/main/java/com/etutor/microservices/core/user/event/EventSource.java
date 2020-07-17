package com.etutor.microservices.core.user.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSource {

    @Output("user-output")
    MessageChannel output();

}
