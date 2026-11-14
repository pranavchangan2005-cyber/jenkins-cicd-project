package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testAddition() {
        assertEquals(30, App.add(10, 20));
    }

    @Test
    void testGreeting() {
        assertEquals("Hello, Pranav!", App.greet("Pranav"));
    }

    @Test
    void testAdditionWithNegativeNumbers() {
        assertEquals(-5, App.add(-2, -3));
    }
}
