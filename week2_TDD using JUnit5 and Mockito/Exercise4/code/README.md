# Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup and Teardown Methods

## Objective
This exercise demonstrates how to organize tests using the **Arrange-Act-Assert (AAA) pattern** and utilize **setup and teardown methods** with `@Before` and `@After` annotations in JUnit.

## Project Structure
```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               └── BankAccount.java                    # Main class for testing
└── test/
    └── java/
        └── com/
            └── example/
                ├── SimpleAAAPatternTest.java           # ✅ Core exercise solution
                ├── BankAccountAAATest.java             # Comprehensive AAA examples
                └── AdvancedAAAPatternTest.java         # Advanced patterns & fixtures
```

## Exercise Requirements ✅

### ✅ Step 1: Write tests using the AAA pattern
**COMPLETED** - All test classes demonstrate the AAA pattern structure:
- **Arrange**: Set up test data and conditions
- **Act**: Execute the method under test
- **Assert**: Verify the expected results

### ✅ Step 2: Use @Before and @After annotations for setup and teardown
**COMPLETED** - Implemented in all test classes:
- `@Before` methods for test fixture setup
- `@After` methods for cleanup and teardown

## AAA Pattern Explained

### What is AAA Pattern?
The **Arrange-Act-Assert** pattern is a standard way of writing unit tests:

1. **🔧 Arrange**: Set up the test data, create objects, configure mocks
2. **⚡ Act**: Execute the method or functionality being tested
3. **✅ Assert**: Verify that the expected results occurred

### Benefits:
- **Clarity**: Tests are easy to read and understand
- **Consistency**: All tests follow the same structure
- **Maintainability**: Changes are easier to make
- **Debugging**: Failures are easier to locate

## Test Classes Overview

### 1. SimpleAAAPatternTest.java (Core Exercise Solution)
**Purpose**: Demonstrates basic AAA pattern with `@Before` and `@After`

**Key Features:**
- Simple test fixture setup with `@Before`
- Basic AAA pattern in test methods
- Cleanup with `@After`
- Exception testing

**Example:**
```java
@Before
public void setUp() {
    // Setup: Create test fixture
    account = new BankAccount("12345", "Test User", 100.0);
}

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

@After
public void tearDown() {
    // Cleanup: Reset test fixture
    account = null;
}
```

### 2. BankAccountAAATest.java (Comprehensive Examples)
**Purpose**: Comprehensive demonstration of AAA pattern with real-world scenarios

**Key Features:**
- Multiple test fixtures
- Complex AAA scenarios
- Detailed assertion messages
- Exception handling tests
- Transaction verification

**Test Methods (12 tests):**
1. `testDeposit_ValidAmount_ShouldIncreaseBalance()`
2. `testWithdraw_ValidAmount_ShouldDecreaseBalance()`
3. `testWithdraw_InsufficientFunds_ShouldThrowException()`
4. `testTransfer_ValidAmount_ShouldUpdateBothAccounts()`
5. `testCalculateInterest_ValidRate_ShouldReturnCorrectAmount()`
6. `testApplyInterest_ValidRate_ShouldUpdateBalance()`
7. `testCloseAccount_ActiveAccount_ShouldMakeAccountInactive()`
8. `testDeposit_InactiveAccount_ShouldThrowException()`
9. `testReactivateAccount_ClosedAccount_ShouldMakeAccountActive()`
10. `testDeposit_MinimumValidAmount_ShouldWork()`
11. `testDeposit_NegativeAmount_ShouldThrowException()`

### 3. AdvancedAAAPatternTest.java (Advanced Patterns)
**Purpose**: Advanced test fixture management and complex AAA scenarios

**Key Features:**
- `@BeforeClass` and `@AfterClass` for class-level setup
- Multiple account fixtures
- Complex multi-step operations
- Error recovery testing
- Data consistency verification

**Test Methods (7 tests):**
1. `testMultipleAccountOperations_ComplexScenario_ShouldMaintainConsistency()`
2. `testBusinessAccountOperations_LargeAmounts_ShouldHandleCorrectly()`
3. `testCascadeTransfers_MultipleAccounts_ShouldMaintainTotalBalance()`
4. `testErrorRecovery_FailedOperations_ShouldNotAffectAccountState()`
5. `testAccountLifecycle_CreateToClose_ShouldTrackAllStates()`
6. `testDataConsistency_ConcurrentOperations_ShouldMaintainIntegrity()`

## Test Fixtures and Setup/Teardown

### Setup Methods (@Before)
```java
@Before
public void setUp() {
    // Create fresh test fixtures for each test
    account = new BankAccount(ACCOUNT_NUMBER, ACCOUNT_HOLDER, INITIAL_BALANCE);
    secondAccount = new BankAccount("67890", "Jane Smith", 500.0);
}
```

### Teardown Methods (@After)
```java
@After
public void tearDown() {
    // Clean up resources
    account = null;
    secondAccount = null;
}
```

### Class-level Setup (@BeforeClass/@AfterClass)
```java
@BeforeClass
public static void setUpClass() {
    // One-time setup for entire test class
    testEnvironment = "TEST";
    testStartTime = System.currentTimeMillis();
}

@AfterClass
public static void tearDownClass() {
    // One-time cleanup after all tests
    long testDuration = System.currentTimeMillis() - testStartTime;
    System.out.println("Total Test Duration: " + testDuration + "ms");
}
```

## BankAccount Class Features

The `BankAccount` class provides realistic banking operations for testing:

### Core Operations:
- `deposit(amount)` - Add money to account
- `withdraw(amount)` - Remove money from account
- `transfer(targetAccount, amount)` - Transfer between accounts
- `calculateInterest(rate)` - Calculate interest without applying
- `applyInterest(rate)` - Calculate and apply interest

### Account Management:
- `closeAccount()` - Deactivate account
- `reactivateAccount()` - Reactivate closed account
- Transaction history tracking
- Balance validation and error handling

## How to Run

### Prerequisites
- Java 11 or higher
- Internet connection (for downloading JUnit JARs)

### Running Tests

#### Option 1: Use batch scripts (Recommended)
```bash
# Run all tests
.\run-tests.bat

# Or just compile
.\compile.bat
```

#### Option 2: Manual execution
```bash
# Compile and run specific test
javac -d target\classes src\main\java\com\example\*.java
javac -d target\test-classes -cp "target\classes;lib\*" src\test\java\com\example\*.java
java -cp "target\classes;target\test-classes;lib\*" org.junit.runner.JUnitCore com.example.SimpleAAAPatternTest
```

## Expected Test Results

### SimpleAAAPatternTest
```
JUnit version 4.13.2
@Before: Test fixture created
@After: Test fixture cleaned up
@Before: Test fixture created
@After: Test fixture cleaned up
@Before: Test fixture created
@After: Test fixture cleaned up
...
Time: 0.xxx
OK (3 tests)
```

### All Tests Combined
```
Total Tests: 22 tests across 3 test classes
- SimpleAAAPatternTest: 3 tests
- BankAccountAAATest: 12 tests  
- AdvancedAAAPatternTest: 7 tests
```

## Key Learning Outcomes

After completing this exercise, you will understand:

### AAA Pattern:
✅ **Structure**: How to organize tests with Arrange-Act-Assert
✅ **Clarity**: Writing readable and maintainable tests
✅ **Consistency**: Following standard testing patterns

### Test Fixtures:
✅ **Setup**: Creating consistent test data with `@Before`
✅ **Cleanup**: Proper resource management with `@After`
✅ **Isolation**: Ensuring tests don't interfere with each other

### Advanced Concepts:
✅ **Class-level Setup**: Using `@BeforeClass` and `@AfterClass`
✅ **Multiple Fixtures**: Managing complex test data scenarios
✅ **Error Testing**: Verifying exception handling
✅ **State Verification**: Checking object state after operations

## Best Practices Demonstrated

1. **Descriptive Test Names**: Clear, intention-revealing method names
2. **Single Responsibility**: Each test verifies one specific behavior
3. **Custom Messages**: Meaningful assertion messages for better failure reporting
4. **Resource Management**: Proper cleanup in teardown methods
5. **Edge Case Testing**: Boundary conditions and error scenarios
6. **Data Isolation**: Fresh fixtures for each test method

## AAA Pattern Benefits

### Before AAA:
```java
@Test
public void testTransfer() {
    BankAccount acc1 = new BankAccount("123", "User", 1000);
    BankAccount acc2 = new BankAccount("456", "User2", 500);
    acc1.transfer(acc2, 200);
    assertEquals(800, acc1.getBalance(), 0.01);
    assertEquals(700, acc2.getBalance(), 0.01);
}
```

### With AAA:
```java
@Test
public void testTransfer_ValidAmount_ShouldUpdateBothAccounts() {
    // Arrange
    double transferAmount = 200.0;
    double expectedSourceBalance = 1000.0 - transferAmount;
    double expectedTargetBalance = 500.0 + transferAmount;
    
    // Act
    sourceAccount.transfer(targetAccount, transferAmount);
    
    // Assert
    assertEquals("Source account balance should decrease", 
                expectedSourceBalance, sourceAccount.getBalance(), 0.01);
    assertEquals("Target account balance should increase", 
                expectedTargetBalance, targetAccount.getBalance(), 0.01);
}
```

## Next Steps
- Practice writing your own AAA pattern tests
- Experiment with different test fixture scenarios
- Learn about parameterized tests for data-driven testing
- Explore test suites for organizing related tests
- Move on to mocking frameworks like Mockito
