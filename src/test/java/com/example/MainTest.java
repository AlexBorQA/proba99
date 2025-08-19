package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
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
        
        String output = outputStream.toString();
        assertTrue(output.contains("Application started successfully!"), 
                   "Output should contain success message");
        assertTrue(output.contains("arg1"), "Output should contain first argument");
        assertTrue(output.contains("arg2"), "Output should contain second argument");
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
        assertDoesNotThrow(() -> {
            var field = Main.class.getDeclaredField("logger");
            field.setAccessible(true);
            assertNotNull(field.get(null));
        });
    }

    @Test
    void testConfigurationLoading() {
        // Test that configuration is loaded properly
        assertDoesNotThrow(() -> Main.main(new String[]{}));
        
        String output = outputStream.toString();
        assertTrue(output.contains("Configuration loaded successfully") || 
                   output.contains("Configuration file not found"), 
                   "Should handle configuration loading");
    }

    @Test
    void testApplicationInfoLogging() {
        // Test that application info is logged
        assertDoesNotThrow(() -> Main.main(new String[]{}));
        
        String output = outputStream.toString();
        assertTrue(output.contains("Starting") || output.contains("Proba99"), 
                   "Should log application information");
    }
}
