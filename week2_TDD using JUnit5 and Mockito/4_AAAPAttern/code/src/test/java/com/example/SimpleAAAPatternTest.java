package com.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Simple demonstration of AAA Pattern with @Before and @After annotations
 * This is the core exercise solution showing the essential concepts.
 */
public class SimpleAAAPatternTest {
    
    // Test fixture
    private BankAccount account;
    
    /**
     * Setup method using @Before annotation
     * Runs before each test method
     */
    @Before
    public void setUp() {
        // Setup: Create test fixture
        account = new BankAccount("12345", "Test User", 100.0);
        System.out.println("@Before: Test fixture created");
    }
    
    /**
     * Teardown method using @After annotation
     * Runs after each test method
     */
    @After
    public void tearDown() {
        // Cleanup: Reset test fixture
        account = null;
        System.out.println("@After: Test fixture cleaned up");
    }
    
    /**
     * Simple AAA pattern example - Deposit test
     */
    @Test
    public void testDeposit() {
        // Arrange
        double depositAmount = 50.0;
        double expectedBalance = 150.0;
        
        // Act
        account.deposit(depositAmount);
        
        // Assert
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }
    
    /**
     * Simple AAA pattern example - Withdrawal test
     */
    @Test
    public void testWithdraw() {
        // Arrange
        double withdrawAmount = 30.0;
        double expectedBalance = 70.0;
        
        // Act
        account.withdraw(withdrawAmount);
        
        // Assert
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }
    
    /**
     * Simple AAA pattern example - Exception test
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawInsufficientFunds() {
        // Arrange
        double withdrawAmount = 150.0; // More than balance
        
        // Act
        account.withdraw(withdrawAmount); // Should throw exception
        
        // Assert (implicit through expected exception)
    }
}
