# Mockito Exercise 1: Mocking and Stubbing

## Overview

This exercise demonstrates how to use Mockito for mocking and stubbing external dependencies in unit tests.

## Scenario

We need to test a service (`MyService`) that depends on an external API (`ExternalApi`). Instead of making real API calls during testing, we use Mockito to mock the external API and stub its methods with predefined values.

## Key Concepts Demonstrated

### 1. Mocking

- Creating mock objects using `@Mock` annotation
- Creating mock objects using `Mockito.mock()` method
- Initializing mocks with `MockitoAnnotations.openMocks()`

### 2. Stubbing

- Using `when().thenReturn()` to define mock behavior
- Stubbing methods with different return values
- Using argument matchers like `anyString()`

### 3. Verification

- Using `verify()` to check if mock methods were called
- Using `verify(mock, times(n))` to verify call count
- Using `verify(mock, never())` to ensure methods weren't called

## Project Structure

```
src/
├── main/java/com/example/
│   ├── ExternalApi.java      # Interface to be mocked
│   └── MyService.java        # Service under test
└── test/java/com/example/
    └── MyServiceTest.java    # Test class with mocking examples
```

## Running the Exercise

### 🚀 Quick Start (Recommended)

```bash
run-exercise.bat
```

This will give you multiple options to run the exercise based on your setup.

### Option 1: Simple Demo (No Dependencies Required)

```bash
java -cp target\classes com.example.MockingDemo
```

### Option 2: Simple Test Runner (Shows Mocking Concepts)

```bash
java -cp target\classes com.example.SimpleTestRunner
```

### Option 3: Full Mockito Tests

#### Using Maven (if available)

```bash
mvn test
```

#### Using Downloaded JARs

```bash
download-dependencies.bat
run-tests-standalone.bat
```

#### Using IDE

Right-click on `MyServiceTest.java` and select "Run Tests"

## Test Cases Included

1. **Basic Mocking Test** - As specified in the exercise requirements
2. **Service Unavailable Test** - Testing error conditions
3. **Parameterized Method Test** - Testing with method parameters
4. **Input Validation Test** - Testing with invalid inputs
5. **Data Processing Test** - Testing business logic
6. **Argument Matchers Test** - Using Mockito argument matchers
7. **Multiple Stubbing Test** - Different behaviors for different inputs

## Dependencies

- JUnit 5.10.0
- Mockito 5.5.0
- Java 11+

## Learning Outcomes

After completing this exercise, you will understand:

- How to create and use mock objects
- How to stub method calls with predefined return values
- How to verify mock interactions
- How to test services with external dependencies
- Best practices for unit testing with mocks
