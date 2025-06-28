package com.example;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

/**
 * Comprehensive JUnit Assertions Demo
 * This class demonstrates all major JUnit assertion methods with practical examples.
 */
public class ComprehensiveAssertionsTest {
    
    private String testString;
    private List<Integer> testList;
    
    @Before
    public void setUp() {
        testString = "Hello JUnit";
        testList = Arrays.asList(1, 2, 3, 4, 5);
    }
    
    @Test
    public void testBasicAssertions() {
        // assertEquals - check if two values are equal
        assertEquals("Testing integer equality", 10, 5 + 5);
        assertEquals("Testing string equality", "Hello", "Hello");
        assertEquals("Testing double equality with delta", 3.14, 22.0/7.0, 0.01);
        
        // assertNotEquals - check if two values are not equal
        assertNotEquals("Values should not be equal", 5, 3);
        assertNotEquals("Strings should not be equal", "Hello", "World");
    }
    
    @Test
    public void testBooleanAssertions() {
        // assertTrue - check if condition is true
        assertTrue("5 should be greater than 3", 5 > 3);
        assertTrue("String should contain substring", testString.contains("JUnit"));
        assertTrue("List should not be empty", !testList.isEmpty());
        
        // assertFalse - check if condition is false
        assertFalse("5 should not be less than 3", 5 < 3);
        assertFalse("String should not contain 'Python'", testString.contains("Python"));
        assertFalse("List should not be empty", testList.isEmpty());
    }
    
    @Test
    public void testNullAssertions() {
        String nullString = null;
        String nonNullString = "Not null";
        
        // assertNull - check if object is null
        assertNull("String should be null", nullString);
        
        // assertNotNull - check if object is not null
        assertNotNull("String should not be null", nonNullString);
        assertNotNull("Test string should not be null", testString);
        assertNotNull("Test list should not be null", testList);
    }
    
    @Test
    public void testSameAssertions() {
        String str1 = "Hello";
        String str2 = str1; // Same reference
        String str3 = new String("Hello"); // Different reference, same content
        
        // assertSame - check if two references point to the same object
        assertSame("Should be the same object reference", str1, str2);
        
        // assertNotSame - check if two references point to different objects
        assertNotSame("Should be different object references", str1, str3);
    }
    
    @Test
    public void testArrayAssertions() {
        int[] expectedArray = {1, 2, 3, 4, 5};
        int[] actualArray = {1, 2, 3, 4, 5};
        int[] differentArray = {1, 2, 3};
        
        // assertArrayEquals - check if arrays have same content
        assertArrayEquals("Arrays should have same content", expectedArray, actualArray);
        
        // This would fail:
        // assertArrayEquals("Arrays should be different", expectedArray, differentArray);
    }
    
    @Test
    public void testStringAssertions() {
        String message = "JUnit is awesome for testing!";
        
        // Using assertTrue for string operations
        assertTrue("String should start with 'JUnit'", message.startsWith("JUnit"));
        assertTrue("String should end with '!'", message.endsWith("!"));
        assertTrue("String should contain 'awesome'", message.contains("awesome"));
        
        // Using assertEquals for string operations
        assertEquals("String length should be 29", 29, message.length());
        assertEquals("Uppercase should match", "JUNIT IS AWESOME FOR TESTING!", message.toUpperCase());
    }
    
    @Test
    public void testNumericAssertions() {
        double pi = 3.14159;
        float piFloat = 3.14159f;
        
        // assertEquals with delta for floating point numbers
        assertEquals("Pi should be approximately 3.14", 3.14, pi, 0.01);
        assertEquals("Pi float should match", 3.14159f, piFloat, 0.00001f);
        
        // Testing ranges
        assertTrue("Pi should be between 3 and 4", pi > 3.0 && pi < 4.0);
        assertTrue("Pi should be positive", pi > 0);
    }
    
    @Test
    public void testCollectionAssertions() {
        List<String> fruits = Arrays.asList("apple", "banana", "cherry");
        
        // Test collection properties
        assertEquals("List should have 3 elements", 3, fruits.size());
        assertTrue("List should contain 'apple'", fruits.contains("apple"));
        assertFalse("List should not contain 'orange'", fruits.contains("orange"));
        
        // Test list ordering
        assertEquals("First element should be 'apple'", "apple", fruits.get(0));
        assertEquals("Last element should be 'cherry'", "cherry", fruits.get(fruits.size() - 1));
    }
    
    @Test
    public void testAssertionsWithCustomMessages() {
        int userAge = 25;
        String userRole = "admin";
        
        // Custom messages make test failures more descriptive
        assertTrue("User must be at least 18 years old to register", userAge >= 18);
        assertNotNull("User role cannot be null", userRole);
        assertEquals("User should have admin privileges", "admin", userRole.toLowerCase());
        
        // Assert with formatted messages
        String expectedName = "John Doe";
        String actualName = "John Doe";
        assertEquals(String.format("Expected user name '%s' but got '%s'", expectedName, actualName), 
                    expectedName, actualName);
    }
}
