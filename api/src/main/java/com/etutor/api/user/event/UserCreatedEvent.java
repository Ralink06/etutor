package com.etutor.api.user.event;

import com.etutor.api.Event;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreatedEvent extends Event<String> {

    private String registrationToken;
    private String firstName;
    private String lastName;
    private String email;

    @Builder
    public UserCreatedEvent(String key,
        LocalDateTime now,
        String registrationToken,
        String firstName,
        String lastName, String email) {
        super(key, now);
        this.registrationToken = registrationToken;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
