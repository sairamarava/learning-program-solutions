package com.example;

/**
 * Simple test runner that demonstrates the mocking concepts without requiring external JARs.
 * This shows the core concepts of the exercise in a runnable format.
 */
public class SimpleTestRunner {
    
    private static int testsPassed = 0;
    private static int testsTotal = 0;
    
    /**
     * Simple assertion method
     */
    public static void assertEquals(String expected, String actual, String testName) {
        testsTotal++;
        if (expected.equals(actual)) {
            System.out.println("✅ PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("❌ FAIL: " + testName);
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual: " + actual);
        }
    }
    
    /**
     * Mock implementation of ExternalApi for testing
     */
    static class MockExternalApi implements ExternalApi {
        private String dataToReturn;
        private boolean availabilityToReturn;
        private String dataByIdToReturn;
        
        // Simple stubbing methods
        public void stubGetData(String data) {
            this.dataToReturn = data;
        }
        
        public void stubIsAvailable(boolean available) {
            this.availabilityToReturn = available;
        }
        
        public void stubGetDataById(String data) {
            this.dataByIdToReturn = data;
        }
        
        @Override
        public String getData() {
            return dataToReturn;
        }
        
        @Override
        public boolean isAvailable() {
            return availabilityToReturn;
        }
        
        @Override
        public String getDataById(String id) {
            return dataByIdToReturn != null ? dataByIdToReturn : "Data for " + id;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Mockito Exercise 1 - Simple Test Runner ===\n");
        System.out.println("This demonstrates the core concepts without requiring external JARs.\n");
        
        // Test 1: Basic mocking and stubbing (as per exercise requirement)
        testBasicMocking();
        
        // Test 2: Service unavailable scenario
        testServiceUnavailable();
        
        // Test 3: Data processing
        testDataProcessing();
        
        // Test 4: Invalid input handling
        testInvalidInput();
        
        // Summary
        System.out.println("\n=== Test Results ===");
        System.out.println("Tests passed: " + testsPassed + "/" + testsTotal);
        if (testsPassed == testsTotal) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("⚠️  Some tests failed.");
        }
        
        System.out.println("\n=== Key Concepts Demonstrated ===");
        System.out.println("1. ✅ Created a mock object for the external API (MockExternalApi)");
        System.out.println("2. ✅ Stubbed the methods to return predefined values (stubGetData, etc.)");
        System.out.println("3. ✅ Wrote test cases that use the mock object");
        System.out.println("\nNote: In real Mockito, you would use @Mock annotations and when().thenReturn() syntax.");
        System.out.println("This simple version demonstrates the same concepts without external dependencies.");
    }
    
    /**
     * Test 1: Basic mocking test as specified in the exercise
     */
    public static void testBasicMocking() {
        System.out.println("--- Test 1: Basic Mocking ---");
        
        // Create a mock object for the external API
        MockExternalApi mockApi = new MockExternalApi();
        
        // Stub the methods to return predefined values
        mockApi.stubGetData("Mock Data");
        mockApi.stubIsAvailable(true);
        
        // Create service with mock
        MyService service = new MyService(mockApi);
        
        // Test the service
        String result = service.fetchData();
        
        // Verify the result
        assertEquals("Mock Data", result, "Basic mocking test");
        System.out.println();
    }
    
    /**
     * Test 2: Service unavailable scenario
     */
    public static void testServiceUnavailable() {
        System.out.println("--- Test 2: Service Unavailable ---");
        
        MockExternalApi mockApi = new MockExternalApi();
        mockApi.stubIsAvailable(false);
        
        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        
        assertEquals("Service unavailable", result, "Service unavailable test");
        System.out.println();
    }
    
    /**
     * Test 3: Data processing
     */
    public static void testDataProcessing() {
        System.out.println("--- Test 3: Data Processing ---");
        
        MockExternalApi mockApi = new MockExternalApi();
        mockApi.stubIsAvailable(true);
        mockApi.stubGetData("Raw Data");
        
        MyService service = new MyService(mockApi);
        String result = service.processData();
        
        assertEquals("Processed: Raw Data", result, "Data processing test");
        System.out.println();
    }
    
    /**
     * Test 4: Invalid input handling
     */
    public static void testInvalidInput() {
        System.out.println("--- Test 4: Invalid Input ---");
        
        MockExternalApi mockApi = new MockExternalApi();
        MyService service = new MyService(mockApi);
        
        String result1 = service.fetchDataById(null);
        assertEquals("Invalid ID", result1, "Null ID test");
        
        String result2 = service.fetchDataById("");
        assertEquals("Invalid ID", result2, "Empty ID test");
        
        String result3 = service.fetchDataById("   ");
        assertEquals("Invalid ID", result3, "Whitespace ID test");
        System.out.println();
    }
}
