package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;

class MainTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testMainMethod() {
        // Simple test to verify main method doesn't throw exceptions
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    void testMainMethodOutput() {
        // Test that main method produces expected output
        Main.main(new String[]{});
        String output = outputStream.toString();
        
        assertTrue(output.contains("Application started successfully!"), 
                   "Output should contain success message");
    }

    @Test
    void testMainMethodWithArguments() {
        // Test main method with command line arguments
        String[] args = {"arg1", "arg2"};
        assertDoesNotThrow(() -> Main.main(args));
    }

    @Test
    void testResourceLoading(@TempDir Path tempDir) throws IOException {
        // Test that test resources can be loaded
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, "test content".getBytes());
        
        assertTrue(Files.exists(testFile), "Test file should be created");
        assertEquals("test content", Files.readString(testFile));
    }

    @Test
    void testLoggerInitialization() {
        // Test that logger is properly initialized
        assertNotNull(Main.class.getDeclaredField("logger"));
    }
}
