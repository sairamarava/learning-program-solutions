package com.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup and Teardown Methods
 * 
 * This test class demonstrates:
 * 1. AAA pattern in test methods
 * 2. @Before and @After annotations for setup and teardown
 * 3. Test fixtures for consistent test state
 */
public class BankAccountAAATest {
    
    // Test fixtures - shared test data
    private BankAccount account;
    private BankAccount secondAccount;
    private final String ACCOUNT_NUMBER = "12345";
    private final String ACCOUNT_HOLDER = "John Doe";
    private final double INITIAL_BALANCE = 1000.0;
    
    /**
     * Setup method - runs before each test
     * Creates fresh test fixtures for each test method
     */
    @Before
    public void setUp() {
        System.out.println("Setting up test fixtures...");
        
        // Arrange: Create test fixtures
        account = new BankAccount(ACCOUNT_NUMBER, ACCOUNT_HOLDER, INITIAL_BALANCE);
        secondAccount = new BankAccount("67890", "Jane Smith", 500.0);
        
        System.out.println("Test fixtures created successfully");
    }
    
    /**
     * Teardown method - runs after each test
     * Cleans up resources and verifies final state
     */
    @After
    public void tearDown() {
        System.out.println("Tearing down test fixtures...");
        
        // Cleanup: Reset test fixtures
        account = null;
        secondAccount = null;
        
        System.out.println("Test cleanup completed\n");
    }
    
    /**
     * Test deposit functionality using AAA pattern
     */
    @Test
    public void testDeposit_ValidAmount_ShouldIncreaseBalance() {
        // Arrange
        double depositAmount = 250.0;
        double expectedBalance = INITIAL_BALANCE + depositAmount;
        int initialTransactionCount = account.getTransactionCount();
        
        // Act
        account.deposit(depositAmount);
        
        // Assert
        assertEquals("Balance should increase by deposit amount", 
                    expectedBalance, account.getBalance(), 0.01);
        assertEquals("Transaction count should increase", 
                    initialTransactionCount + 1, account.getTransactionCount());
        assertTrue("Transaction history should contain deposit record",
                  account.getTransactionHistory().toString().contains("Deposited: $250.0"));
    }
    
    /**
     * Test withdrawal functionality using AAA pattern
     */
    @Test
    public void testWithdraw_ValidAmount_ShouldDecreaseBalance() {
        // Arrange
        double withdrawAmount = 300.0;
        double expectedBalance = INITIAL_BALANCE - withdrawAmount;
        int initialTransactionCount = account.getTransactionCount();
        
        // Act
        account.withdraw(withdrawAmount);
        
        // Assert
        assertEquals("Balance should decrease by withdrawal amount", 
                    expectedBalance, account.getBalance(), 0.01);
        assertEquals("Transaction count should increase", 
                    initialTransactionCount + 1, account.getTransactionCount());
        assertTrue("Transaction history should contain withdrawal record",
                  account.getTransactionHistory().toString().contains("Withdrew: $300.0"));
    }
    
    /**
     * Test exception handling for insufficient funds using AAA pattern
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdraw_InsufficientFunds_ShouldThrowException() {
        // Arrange
        double withdrawAmount = INITIAL_BALANCE + 100.0; // More than available balance
        double balanceBeforeAttempt = account.getBalance();
        
        // Act
        account.withdraw(withdrawAmount); // Should throw exception
        
        // Assert (implicit - exception expected)
        // Additional verification if exception handling changes
        assertEquals("Balance should remain unchanged after failed withdrawal", 
                    balanceBeforeAttempt, account.getBalance(), 0.01);
    }
    
    /**
     * Test transfer functionality between accounts using AAA pattern
     */
    @Test
    public void testTransfer_ValidAmount_ShouldUpdateBothAccounts() {
        // Arrange
        double transferAmount = 200.0;
        double expectedSourceBalance = INITIAL_BALANCE - transferAmount;
        double expectedTargetBalance = 500.0 + transferAmount; // secondAccount initial balance is 500
        
        // Act
        account.transfer(secondAccount, transferAmount);
        
        // Assert
        assertEquals("Source account balance should decrease", 
                    expectedSourceBalance, account.getBalance(), 0.01);
        assertEquals("Target account balance should increase", 
                    expectedTargetBalance, secondAccount.getBalance(), 0.01);
        assertTrue("Source account should have transfer record",
                  account.getTransactionHistory().toString().contains("Transferred: $200.0"));
        assertTrue("Target account should have received transfer record",
                  secondAccount.getTransactionHistory().toString().contains("Received transfer: $200.0"));
    }
    
    /**
     * Test interest calculation using AAA pattern
     */
    @Test
    public void testCalculateInterest_ValidRate_ShouldReturnCorrectAmount() {
        // Arrange
        double interestRate = 5.0; // 5%
        double expectedInterest = INITIAL_BALANCE * (interestRate / 100);
        
        // Act
        double actualInterest = account.calculateInterest(interestRate);
        
        // Assert
        assertEquals("Interest calculation should be correct", 
                    expectedInterest, actualInterest, 0.01);
        assertEquals("Balance should remain unchanged during calculation", 
                    INITIAL_BALANCE, account.getBalance(), 0.01);
    }
    
    /**
     * Test interest application using AAA pattern
     */
    @Test
    public void testApplyInterest_ValidRate_ShouldUpdateBalance() {
        // Arrange
        double interestRate = 3.0; // 3%
        double expectedInterest = INITIAL_BALANCE * (interestRate / 100);
        double expectedNewBalance = INITIAL_BALANCE + expectedInterest;
        int initialTransactionCount = account.getTransactionCount();
        
        // Act
        account.applyInterest(interestRate);
        
        // Assert
        assertEquals("Balance should increase by interest amount", 
                    expectedNewBalance, account.getBalance(), 0.01);
        assertEquals("Transaction count should increase", 
                    initialTransactionCount + 1, account.getTransactionCount());
        assertTrue("Transaction history should contain interest record",
                  account.getTransactionHistory().toString().contains("Interest applied"));
    }
    
    /**
     * Test account closure using AAA pattern
     */
    @Test
    public void testCloseAccount_ActiveAccount_ShouldMakeAccountInactive() {
        // Arrange
        assertTrue("Account should initially be active", account.isActive());
        
        // Act
        account.closeAccount();
        
        // Assert
        assertFalse("Account should be inactive after closure", account.isActive());
        assertTrue("Transaction history should contain closure record",
                  account.getTransactionHistory().toString().contains("Account closed"));
    }
    
    /**
     * Test operations on closed account using AAA pattern
     */
    @Test(expected = IllegalStateException.class)
    public void testDeposit_InactiveAccount_ShouldThrowException() {
        // Arrange
        account.closeAccount(); // Make account inactive
        double depositAmount = 100.0;
        
        // Act
        account.deposit(depositAmount); // Should throw exception
        
        // Assert (implicit - exception expected)
    }
    
    /**
     * Test account reactivation using AAA pattern
     */
    @Test
    public void testReactivateAccount_ClosedAccount_ShouldMakeAccountActive() {
        // Arrange
        account.closeAccount();
        assertFalse("Account should be inactive after closure", account.isActive());
        
        // Act
        account.reactivateAccount();
        
        // Assert
        assertTrue("Account should be active after reactivation", account.isActive());
        assertTrue("Transaction history should contain reactivation record",
                  account.getTransactionHistory().toString().contains("Account reactivated"));
    }
    
    /**
     * Test boundary conditions using AAA pattern
     */
    @Test
    public void testDeposit_MinimumValidAmount_ShouldWork() {
        // Arrange
        double minimumAmount = 0.01; // Smallest positive amount
        double expectedBalance = INITIAL_BALANCE + minimumAmount;
        
        // Act
        account.deposit(minimumAmount);
        
        // Assert
        assertEquals("Balance should increase even with minimum amount", 
                    expectedBalance, account.getBalance(), 0.001);
    }
    
    /**
     * Test invalid input handling using AAA pattern
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeposit_NegativeAmount_ShouldThrowException() {
        // Arrange
        double negativeAmount = -50.0;
        double balanceBeforeAttempt = account.getBalance();
        
        // Act
        account.deposit(negativeAmount); // Should throw exception
        
        // Assert (implicit - exception expected)
    }
}
