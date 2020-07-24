package com.etutor.api;

import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Event<K> {

    private K key;
    private LocalDateTime createdAt;

    public Event(K key, LocalDateTime now) {
        this.key = key;
        this.createdAt = now;
    }

    public K getKey() {
        return key;
    }

    public LocalDateTime getEventCreatedAt() {
        return createdAt;
    }
}
