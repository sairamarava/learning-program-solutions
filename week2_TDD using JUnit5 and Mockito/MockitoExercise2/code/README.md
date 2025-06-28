# Exercise 2: Verifying Interactions with Mockito

## Scenario

You need to ensure that a method is called with specific arguments using Mockito.

## Steps

1. Create a mock object
2. Call the method with specific arguments
3. Verify the interaction

## Files Created

- `ExternalApi.java` - Interface representing an external API
- `MyService.java` - Service class that uses the external API
- `MyServiceTest.java` - Test class that verifies interactions
- `pom.xml` - Maven configuration with JUnit 5 and Mockito dependencies

## How to Run

1. Navigate to the code directory
2. Run: `mvn test` to execute the tests

## What the Test Does

The test verifies that when `MyService.fetchData()` is called, it internally calls `ExternalApi.getData()` exactly once.
