package com.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

/**
 * Advanced AAA Pattern and Test Fixtures Demo
 * 
 * This class demonstrates:
 * 1. Multiple test fixture scenarios
 * 2. Class-level setup and teardown (@BeforeClass, @AfterClass)
 * 3. Complex AAA pattern examples
 * 4. Data-driven test scenarios
 */
public class AdvancedAAAPatternTest {
    
    // Class-level test data (shared across all test methods)
    private static String testEnvironment;
    private static long testStartTime;
    
    // Instance-level test fixtures (recreated for each test)
    private BankAccount primaryAccount;
    private BankAccount savingsAccount;
    private BankAccount businessAccount;
    
    // Test data constants
    private static final String PRIMARY_ACCOUNT_NUMBER = "ACC001";
    private static final String SAVINGS_ACCOUNT_NUMBER = "SAV001";
    private static final String BUSINESS_ACCOUNT_NUMBER = "BUS001";
    
    /**
     * Class-level setup - runs once before all tests in this class
     */
    @BeforeClass
    public static void setUpClass() {
        testEnvironment = "TEST";
        testStartTime = System.currentTimeMillis();
        System.out.println("=== Starting Advanced AAA Pattern Test Suite ===");
        System.out.println("Test Environment: " + testEnvironment);
        System.out.println("Test Start Time: " + testStartTime);
    }
    
    /**
     * Class-level teardown - runs once after all tests in this class
     */
    @AfterClass
    public static void tearDownClass() {
        long testEndTime = System.currentTimeMillis();
        long testDuration = testEndTime - testStartTime;
        System.out.println("=== Advanced AAA Pattern Test Suite Completed ===");
        System.out.println("Total Test Duration: " + testDuration + "ms");
        System.out.println("Test Environment: " + testEnvironment + " cleaned up");
    }
    
    /**
     * Instance-level setup - runs before each test method
     */
    @Before
    public void setUp() {
        System.out.println("Setting up test fixtures for individual test...");
        
        // Arrange: Create different types of test fixtures
        primaryAccount = new BankAccount(PRIMARY_ACCOUNT_NUMBER, "Alice Johnson", 2000.0);
        savingsAccount = new BankAccount(SAVINGS_ACCOUNT_NUMBER, "Alice Johnson", 5000.0);
        businessAccount = new BankAccount(BUSINESS_ACCOUNT_NUMBER, "Johnson Enterprises", 10000.0);
        
        System.out.println("Individual test fixtures ready");
    }
    
    /**
     * Instance-level teardown - runs after each test method
     */
    @After
    public void tearDown() {
        System.out.println("Cleaning up individual test fixtures...");
        
        // Cleanup: Ensure all accounts are properly closed
        if (primaryAccount != null && primaryAccount.isActive()) {
            primaryAccount.closeAccount();
        }
        if (savingsAccount != null && savingsAccount.isActive()) {
            savingsAccount.closeAccount();
        }
        if (businessAccount != null && businessAccount.isActive()) {
            businessAccount.closeAccount();
        }
        
        primaryAccount = null;
        savingsAccount = null;
        businessAccount = null;
        
        System.out.println("Individual test cleanup completed\n");
    }
    
    /**
     * Test multiple account operations using AAA pattern
     */
    @Test
    public void testMultipleAccountOperations_ComplexScenario_ShouldMaintainConsistency() {
        // Arrange
        double transferAmount = 500.0;
        double interestRate = 2.5;
        
        double expectedPrimaryBalance = 2000.0 - transferAmount; // After transfer out
        double expectedSavingsBalance = 5000.0 + transferAmount; // After transfer in
        double savingsInterest = (5000.0 + transferAmount) * (interestRate / 100);
        double expectedSavingsBalanceAfterInterest = expectedSavingsBalance + savingsInterest;
        
        // Act
        primaryAccount.transfer(savingsAccount, transferAmount);
        savingsAccount.applyInterest(interestRate);
        
        // Assert
        assertEquals("Primary account balance should be correct after transfer", 
                    expectedPrimaryBalance, primaryAccount.getBalance(), 0.01);
        assertEquals("Savings account balance should be correct after transfer and interest", 
                    expectedSavingsBalanceAfterInterest, savingsAccount.getBalance(), 0.01);
        
        // Verify transaction histories
        assertTrue("Primary account should have transfer record",
                  primaryAccount.getTransactionHistory().toString().contains("Transferred: $500.0"));
        assertTrue("Savings account should have both transfer and interest records",
                  savingsAccount.getTransactionHistory().toString().contains("Received transfer") &&
                  savingsAccount.getTransactionHistory().toString().contains("Interest applied"));
    }
    
    /**
     * Test business account specific operations using AAA pattern
     */
    @Test
    public void testBusinessAccountOperations_LargeAmounts_ShouldHandleCorrectly() {
        // Arrange
        double largeDepositAmount = 50000.0;
        double largeWithdrawalAmount = 25000.0;
        double expectedBalanceAfterDeposit = 10000.0 + largeDepositAmount;
        double expectedFinalBalance = expectedBalanceAfterDeposit - largeWithdrawalAmount;
        
        // Act
        businessAccount.deposit(largeDepositAmount);
        businessAccount.withdraw(largeWithdrawalAmount);
        
        // Assert
        assertEquals("Business account should handle large amounts correctly", 
                    expectedFinalBalance, businessAccount.getBalance(), 0.01);
        assertEquals("Business account should have correct number of transactions", 
                    3, businessAccount.getTransactionCount()); // 1 initial + 2 operations
    }
    
    /**
     * Test cascade operations across multiple accounts using AAA pattern
     */
    @Test
    public void testCascadeTransfers_MultipleAccounts_ShouldMaintainTotalBalance() {
        // Arrange
        double initialTotalBalance = primaryAccount.getBalance() + 
                                   savingsAccount.getBalance() + 
                                   businessAccount.getBalance();
        
        double transfer1Amount = 300.0; // Primary to Savings
        double transfer2Amount = 1000.0; // Business to Primary
        
        // Act
        primaryAccount.transfer(savingsAccount, transfer1Amount);
        businessAccount.transfer(primaryAccount, transfer2Amount);
        
        // Assert
        double finalTotalBalance = primaryAccount.getBalance() + 
                                 savingsAccount.getBalance() + 
                                 businessAccount.getBalance();
        
        assertEquals("Total balance across all accounts should remain the same", 
                    initialTotalBalance, finalTotalBalance, 0.01);
        
        // Verify individual account states
        assertEquals("Primary account should reflect both transfers", 
                    2000.0 - transfer1Amount + transfer2Amount, primaryAccount.getBalance(), 0.01);
        assertEquals("Savings account should reflect received transfer", 
                    5000.0 + transfer1Amount, savingsAccount.getBalance(), 0.01);
        assertEquals("Business account should reflect sent transfer", 
                    10000.0 - transfer2Amount, businessAccount.getBalance(), 0.01);
    }
    
    /**
     * Test error recovery scenarios using AAA pattern
     */
    @Test
    public void testErrorRecovery_FailedOperations_ShouldNotAffectAccountState() {
        // Arrange
        double initialBalance = primaryAccount.getBalance();
        double invalidWithdrawalAmount = 5000.0; // More than available balance
        int initialTransactionCount = primaryAccount.getTransactionCount();
        
        // Act & Assert (combined for exception testing)
        try {
            primaryAccount.withdraw(invalidWithdrawalAmount);
            fail("Should have thrown IllegalArgumentException for insufficient funds");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
        
        // Assert - account state should be unchanged after failed operation
        assertEquals("Balance should remain unchanged after failed withdrawal", 
                    initialBalance, primaryAccount.getBalance(), 0.01);
        assertEquals("Transaction count should remain unchanged after failed operation", 
                    initialTransactionCount, primaryAccount.getTransactionCount());
    }
    
    /**
     * Test account lifecycle using AAA pattern
     */
    @Test
    public void testAccountLifecycle_CreateToClose_ShouldTrackAllStates() {
        // Arrange
        String tempAccountNumber = "TEMP123";
        String tempAccountHolder = "Temporary User";
        double tempInitialBalance = 100.0;
        
        // Act - Full lifecycle
        BankAccount tempAccount = new BankAccount(tempAccountNumber, tempAccountHolder, tempInitialBalance);
        tempAccount.deposit(50.0);
        tempAccount.withdraw(25.0);
        tempAccount.applyInterest(1.0);
        tempAccount.closeAccount();
        
        // Assert
        assertFalse("Account should be inactive after closure", tempAccount.isActive());
        assertEquals("Account should have correct final balance", 
                    tempInitialBalance + 50.0 - 25.0 + (tempInitialBalance + 25.0) * 0.01, 
                    tempAccount.getBalance(), 0.01);
        assertEquals("Account should have all transactions recorded", 
                    5, tempAccount.getTransactionCount()); // create, deposit, withdraw, interest, close
        
        // Verify that operations fail on closed account
        try {
            tempAccount.deposit(10.0);
            fail("Should not allow operations on closed account");
        } catch (IllegalStateException e) {
            // Expected behavior
        }
    }
    
    /**
     * Test data consistency across operations using AAA pattern
     */
    @Test
    public void testDataConsistency_ConcurrentOperations_ShouldMaintainIntegrity() {
        // Arrange
        BankAccount account1 = new BankAccount("CONS1", "User1", 1000.0);
        BankAccount account2 = new BankAccount("CONS2", "User2", 1000.0);
        
        double transferAmount = 100.0;
        double interestRate = 5.0;
        
        // Act - Simulate concurrent-like operations
        account1.transfer(account2, transferAmount);
        account1.applyInterest(interestRate);
        account2.applyInterest(interestRate);
        
        // Assert - Check data consistency
        double account1Interest = (1000.0 - transferAmount) * (interestRate / 100);
        double account2Interest = (1000.0 + transferAmount) * (interestRate / 100);
        
        double expectedAccount1Balance = 1000.0 - transferAmount + account1Interest;
        double expectedAccount2Balance = 1000.0 + transferAmount + account2Interest;
        
        assertEquals("Account1 balance should be consistent", 
                    expectedAccount1Balance, account1.getBalance(), 0.01);
        assertEquals("Account2 balance should be consistent", 
                    expectedAccount2Balance, account2.getBalance(), 0.01);
        
        // Cleanup
        account1.closeAccount();
        account2.closeAccount();
    }
}
