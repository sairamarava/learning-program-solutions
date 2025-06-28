# Exercise 3: Assertions in JUnit

## Objective
This exercise demonstrates how to use different assertions in JUnit to validate test results effectively.

## Project Structure
```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               └── Student.java                        # Sample class for testing
└── test/
    └── java/
        └── com/
            └── example/
                ├── AssertionsTest.java                 # Basic exercise solution
                ├── ComprehensiveAssertionsTest.java    # Extended assertion examples
                └── StudentTest.java                    # Practical assertion usage
```

## Exercise Requirements ✅

### Step 1: Write tests using various JUnit assertions

**✅ Completed** - Created `AssertionsTest.java` with the exact solution code from the exercise:

```java
public class AssertionsTest { 
    @Test 
    public void testAssertions() { 
        // Assert equals 
        assertEquals(5, 2 + 3); 
 
        // Assert true 
        assertTrue(5 > 3); 
 
        // Assert false 
        assertFalse(5 < 3); 
 
        // Assert null 
        assertNull(null); 
 
        // Assert not null 
        assertNotNull(new Object()); 
    } 
}
```

## What's Included

### 1. AssertionsTest.java
The exact solution from the exercise demonstrating:
- `assertEquals()` - Check if two values are equal
- `assertTrue()` - Check if a condition is true
- `assertFalse()` - Check if a condition is false
- `assertNull()` - Check if an object is null
- `assertNotNull()` - Check if an object is not null

### 2. ComprehensiveAssertionsTest.java
Extended examples demonstrating ALL major JUnit assertions:

#### Basic Assertions:
- `assertEquals()` with different data types
- `assertNotEquals()` for inequality checks
- `assertEquals()` with delta for floating-point numbers

#### Boolean Assertions:
- `assertTrue()` with custom messages
- `assertFalse()` with practical examples

#### Null Assertions:
- `assertNull()` and `assertNotNull()` with real scenarios

#### Reference Assertions:
- `assertSame()` - Check object reference equality
- `assertNotSame()` - Check different object references

#### Array Assertions:
- `assertArrayEquals()` - Compare array contents

#### String and Collection Assertions:
- String operations with assertions
- Collection testing examples

#### Custom Messages:
- Using descriptive messages in assertions
- Formatted assertion messages

### 3. StudentTest.java
Practical demonstration using a real `Student` class:
- Object creation and property validation
- Business logic testing (grade levels, graduation eligibility)
- Boundary condition testing
- Equality and string representation testing

### 4. Student.java
Supporting class that demonstrates:
- Properties: name, age, GPA, active status
- Business methods: grade level calculation, graduation eligibility
- Object equality and string representation

## JUnit Assertions Reference

| Assertion | Purpose | Example |
|-----------|---------|---------|
| `assertEquals(expected, actual)` | Check equality | `assertEquals(5, 2+3)` |
| `assertNotEquals(unexpected, actual)` | Check inequality | `assertNotEquals(5, 3)` |
| `assertTrue(condition)` | Check if true | `assertTrue(5 > 3)` |
| `assertFalse(condition)` | Check if false | `assertFalse(5 < 3)` |
| `assertNull(object)` | Check if null | `assertNull(null)` |
| `assertNotNull(object)` | Check if not null | `assertNotNull(new Object())` |
| `assertSame(expected, actual)` | Check same reference | `assertSame(obj1, obj2)` |
| `assertNotSame(unexpected, actual)` | Check different reference | `assertNotSame(obj1, obj3)` |
| `assertArrayEquals(expected, actual)` | Check array equality | `assertArrayEquals(arr1, arr2)` |

## How to Run

### Prerequisites
- Java 11 or higher
- Internet connection (for downloading JUnit JARs)

### Running Tests

#### Option 1: Use the batch script (Recommended)
```bash
# Run all tests
.\run-tests.bat

# Or just compile
.\compile.bat
```

#### Option 2: Manual compilation and execution
```bash
# Navigate to project directory
cd "C:\Users\veera\javaFSE\learning-program-solutions\week2_TDD using JUnit5 and Mockito\Exercise3\code"

# Compile and run basic exercise
javac -d target\classes src\main\java\com\example\*.java
javac -d target\test-classes -cp "target\classes;lib\*" src\test\java\com\example\*.java
java -cp "target\classes;target\test-classes;lib\*" org.junit.runner.JUnitCore com.example.AssertionsTest
```

### Expected Output
```
JUnit version 4.13.2
.
Time: 0.xxx
OK (1 test)
```

## Test Results Summary

When you run the tests, you should see:

1. **AssertionsTest**: 1 test passing - demonstrates basic assertions
2. **ComprehensiveAssertionsTest**: 8 tests passing - demonstrates all assertion types
3. **StudentTest**: 7 tests passing - demonstrates practical assertion usage

**Total: 16 tests demonstrating comprehensive assertion usage**

## Best Practices Demonstrated

1. **Descriptive Messages**: Using custom messages in assertions for better failure reporting
2. **Appropriate Assertion Selection**: Choosing the right assertion for each scenario
3. **Edge Case Testing**: Testing boundary conditions and special cases
4. **Floating Point Comparison**: Using delta values for double/float comparisons
5. **Null Safety**: Proper testing for null and non-null conditions
6. **Object Equality**: Testing both reference equality and content equality

## Learning Outcomes

After completing this exercise, you will understand:
- How to use all major JUnit assertion methods
- When to use each type of assertion
- How to write descriptive assertion messages
- How to test different data types effectively
- How to handle edge cases in testing
- Best practices for assertion usage in real-world scenarios

## Next Steps
- Practice writing assertions for your own classes
- Learn about exception testing with `@Test(expected = Exception.class)`
- Explore parameterized tests for testing multiple inputs
- Move on to more advanced JUnit features like test suites and custom matchers
