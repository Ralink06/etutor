package com.etutor.microservices.core.user;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApplicationTest {

    @Test
    @Order(value = Integer.MIN_VALUE)
    public void shouldStartUpApplicationContext() {
        //given
        assert true;
    }
}