# Exercise 1: Setting Up JUnit

## Objective

This exercise demonstrates how to set up JUnit in a Java project for writing unit tests.

## Exercise Completion Steps

### ✅ Step 1: Create a new Java project

- Created Maven-based Java project structure
- Set up proper package structure: `com.example`

### ✅ Step 2: Add JUnit dependency

Added JUnit 4.13.2 dependency to `pom.xml` as specified:

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

## What's Included

### Calculator.java

A simple calculator class with basic arithmetic operations:

- `add(int a, int b)` - Addition
- `subtract(int a, int b)` - Subtraction
- `multiply(int a, int b)` - Multiplication
- `divide(int a, int b)` - Division (with zero-division protection)

### CalculatorTest.java

Comprehensive JUnit 4 test class demonstrating:

- **@Before** - Setup method that runs before each test
- **@After** - Teardown method that runs after each test
- **@Test** - Basic test methods
- **@Test(expected = Exception.class)** - Testing expected exceptions

#### Test Methods:

1. `testAdd()` - Tests addition with positive, zero, and negative numbers
2. `testSubtract()` - Tests subtraction operations
3. `testMultiply()` - Tests multiplication operations
4. `testDivide()` - Tests division operations
5. `testDivideByZero()` - Tests exception handling for division by zero
6. `testBooleanAssertions()` - Demonstrates assertTrue/assertFalse
7. `testNotNull()` - Demonstrates assertNotNull

## JUnit 4 Assertions Used

- `assertEquals(expected, actual)` - Check equality
- `assertEquals(expected, actual, delta)` - Check equality with tolerance for doubles
- `assertTrue(condition)` - Check if condition is true
- `assertFalse(condition)` - Check if condition is false
- `assertNotNull(object)` - Check if object is not null

## How to Run

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Commands

```powershell
# Navigate to project directory
cd "C:\Users\veera\javaFSE\learning-program-solutions\week2_TDD using JUnit5 and Mockito\Exercise1\code"

# Compile the project
mvn compile

# Run tests
mvn test

# Run specific test class
mvn test -Dtest=CalculatorTest

# Generate test reports
mvn surefire-report:report
```

## Expected Output

When you run `mvn test`, you should see:

- All tests pass (BUILD SUCCESS)
- Test execution summary showing number of tests run
- Console output from @Before and @After methods

## Learning Outcomes

After completing this exercise, you should understand:

1. How to set up a Maven project with JUnit 4 dependency
2. Basic JUnit 4 annotations (@Test, @Before, @After)
3. Common assertion methods (assertEquals, assertTrue, etc.)
4. How to test expected exceptions
5. Proper test method naming and organization
6. How to run tests using Maven

## Alternative Compilation Methods

### If Maven is not working (Python mvn conflict):

If you encounter Maven issues due to Python package conflicts, use these alternative methods:

#### Method 1: Use the provided batch scripts

```bash
# Compile and run tests
.\run-tests.bat

# Or just compile
.\compile.bat
```

#### Method 2: Manual compilation

```bash
# Create directories
mkdir target\classes
mkdir target\test-classes
mkdir lib

# Download JUnit JARs (if not already downloaded)
# JUnit 4.13.2: https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar
# Hamcrest Core: https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

# Compile main classes
javac -d target\classes src\main\java\com\example\*.java

# Compile test classes
javac -d target\test-classes -cp "target\classes;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" src\test\java\com\example\*.java

# Run tests
java -cp "target\classes;target\test-classes;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore com.example.CalculatorTest
```

### Troubleshooting Maven Issues:

If you see errors like "ModuleNotFoundError: No module named 'pwd'", this indicates a Python package named `mvn` is interfering with the actual Maven executable. The batch scripts provided will work around this issue by using Java directly.

## Next Steps

- Try adding more test cases
- Experiment with other JUnit 4 annotations like @BeforeClass and @AfterClass
- Learn about test suites and parameterized tests
- Move on to JUnit 5 and Mockito exercises
