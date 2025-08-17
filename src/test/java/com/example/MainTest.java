package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMainMethod() {
        // Simple test to verify main method doesn't throw exceptions
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
