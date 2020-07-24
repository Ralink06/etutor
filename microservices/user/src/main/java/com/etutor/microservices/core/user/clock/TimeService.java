package com.etutor.microservices.core.user.clock;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    public LocalDateTime currentDate() {
        return LocalDateTime.now();
    }
}
