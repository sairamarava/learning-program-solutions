package com.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Test class for Calculator using JUnit 4
 * This demonstrates the basic JUnit setup as specified in Exercise 1
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    /**
     * Set up method - runs before each test
     */
    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setting up test...");
    }
    
    /**
     * Tear down method - runs after each test
     */
    @After
    public void tearDown() {
        calculator = null;
        System.out.println("Test completed.");
    }
    
    /**
     * Test addition functionality
     */
    @Test
    public void testAdd() {
        // Test positive numbers
        int result = calculator.add(5, 3);
        assertEquals(8, result);
        
        // Test with zero
        result = calculator.add(5, 0);
        assertEquals(5, result);
        
        // Test negative numbers
        result = calculator.add(-2, -3);
        assertEquals(-5, result);
    }
    
    /**
     * Test subtraction functionality
     */
    @Test
    public void testSubtract() {
        int result = calculator.subtract(10, 3);
        assertEquals(7, result);
        
        result = calculator.subtract(5, 5);
        assertEquals(0, result);
        
        result = calculator.subtract(3, 10);
        assertEquals(-7, result);
    }
    
    /**
     * Test multiplication functionality
     */
    @Test
    public void testMultiply() {
        int result = calculator.multiply(4, 3);
        assertEquals(12, result);
        
        result = calculator.multiply(5, 0);
        assertEquals(0, result);
        
        result = calculator.multiply(-2, 3);
        assertEquals(-6, result);
    }
    
    /**
     * Test division functionality
     */
    @Test
    public void testDivide() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.001);
        
        result = calculator.divide(7, 2);
        assertEquals(3.5, result, 0.001);
    }
    
    /**
     * Test division by zero - should throw exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
    }
    
    /**
     * Test that demonstrates assertTrue and assertFalse
     */
    @Test
    public void testBooleanAssertions() {
        assertTrue("Addition result should be positive", calculator.add(5, 3) > 0);
        assertFalse("Subtraction result should not be negative", calculator.subtract(10, 3) < 0);
    }
    
    /**
     * Test that demonstrates assertNotNull
     */
    @Test
    public void testNotNull() {
        assertNotNull("Calculator instance should not be null", calculator);
    }
}
