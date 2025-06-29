package com.example;

/**
 * A simple Calculator class to demonstrate JUnit testing setup
 */
public class Calculator {
    
    /**
     * Adds two integers
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }
    
    /**
     * Subtracts second number from first
     * @param a first number
     * @param b second number
     * @return difference of a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }
    
    /**
     * Multiplies two integers
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    
    /**
     * Divides first number by second
     * @param a dividend
     * @param b divisor
     * @return quotient of a and b
     * @throws IllegalArgumentException if b is zero
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (double) a / b;
    }
}
