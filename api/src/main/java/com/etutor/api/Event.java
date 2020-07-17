package com.etutor.api;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

public class Event<K, T> {

    public enum Type { CREATED, DELETED, UPDATED }

    private Event.Type eventType;
    private K key;
    private T data;
    private LocalDateTime createdAt;

    public Event() {
        this.eventType = null;
        this.key = null;
        this.data = null;
        this.createdAt = null;
    }

    public Event(Type eventType, K key, T data) {
        this.eventType = eventType;
        this.key = key;
        this.data = data;
        this.createdAt = now();
    }

    public Type getEventType() {
        return eventType;
    }

    public K getKey() {
        return key;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getEventCreatedAt() {
        return createdAt;
    }
}
