# Exercise 1: Mocking and Stubbing - Solution

## Overview

This exercise demonstrates the fundamental concepts of mocking and stubbing using Mockito framework. The solution follows the exact requirements specified in the exercise while providing additional comprehensive examples.

## Files Created

### 1. Core Application Files

- **ExternalApi.java** - Interface representing an external API dependency
- **MyService.java** - Service class that depends on ExternalApi
- **MockingDemo.java** - Demonstration class showing real vs mocked behavior

### 2. Test Files

- **MyServiceTest.java** - Complete test suite with 8 different test scenarios

### 3. Configuration Files

- **pom.xml** - Maven configuration with JUnit 5 and Mockito dependencies
- **README.md** - Comprehensive documentation
- **.gitignore** - Version control ignore patterns

### 4. Build Scripts

- **run-tests.bat** - Batch file to run tests using Maven
- **compile.bat** - Alternative compilation script

## Key Concepts Demonstrated

### 1. Basic Mocking (As Required by Exercise)

```java
@Test
public void testExternalApi() {
    ExternalApi mockApi = Mockito.mock(ExternalApi.class);
    when(mockApi.getData()).thenReturn("Mock Data");
    MyService service = new MyService(mockApi);
    String result = service.fetchData();
    assertEquals("Mock Data", result);
}
```

### 2. Advanced Mocking Techniques

- Using `@Mock` annotations
- Setting up mocks with `@BeforeEach`
- Stubbing multiple methods
- Using argument matchers
- Verifying method calls
- Testing error conditions

### 3. Verification Examples

- `verify(mock).method()` - Verify method was called
- `verify(mock, times(n))` - Verify call count
- `verify(mock, never())` - Verify method was NOT called
- `verify(mock).method(anyString())` - Verify with argument matchers

## Test Scenarios Covered

1. **Basic mocking test** (exact exercise requirement)
2. **Service unavailable test** (error handling)
3. **Parameterized method test** (method with parameters)
4. **Input validation test** (invalid inputs)
5. **Data processing test** (business logic)
6. **Argument matchers test** (flexible matching)
7. **Multiple stubbing test** (different behaviors)

## How to Run

### Method 1: Using Maven

```bash
cd "C:\Users\veera\javaFSE\learning-program-solutions\week2_TDD using JUnit5 and Mockito\MockitoExercise1\code"
mvn clean test
```

### Method 2: Using IDE

1. Open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Right-click on `MyServiceTest.java`
3. Select "Run All Tests" or "Run Test"

### Method 3: Using Batch File

```bash
run-tests.bat
```

### Method 4: Run Demo (No Testing Framework Required)

```bash
javac src/main/java/com/example/*.java
java -cp src/main/java com.example.MockingDemo
```

## Dependencies

- **JUnit Jupiter 5.10.0** - Testing framework
- **Mockito Core 5.5.0** - Mocking framework
- **Mockito JUnit Jupiter 5.5.0** - Integration between Mockito and JUnit 5
- **Java 11+** - Programming language

## Learning Outcomes

After completing this exercise, you understand:

- ✅ How to create mock objects using Mockito
- ✅ How to stub methods to return predefined values
- ✅ How to write test cases that use mock objects
- ✅ How to verify mock interactions
- ✅ How to test services with external dependencies
- ✅ Best practices for unit testing with mocks

## Exercise Requirements Fulfilled

✅ **Step 1**: Create a mock object for the external API  
✅ **Step 2**: Stub the methods to return predefined values  
✅ **Step 3**: Write a test case that uses the mock object

**Plus additional comprehensive examples and best practices!**
