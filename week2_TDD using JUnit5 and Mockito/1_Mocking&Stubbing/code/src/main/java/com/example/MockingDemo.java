package com.example;

/**
 * Simple demonstration class to show the mocking concept without running tests.
 * This can be executed to understand how the service and external API would work in reality.
 */
public class MockingDemo {
    
    /**
     * Real implementation of ExternalApi for demonstration purposes.
     */
    static class RealExternalApi implements ExternalApi {
        @Override
        public String getData() {
            // In real scenario, this would make an HTTP call
            return "Real data from external API";
        }
        
        @Override
        public boolean isAvailable() {
            // In real scenario, this would check API availability
            return true;
        }
        
        @Override
        public String getDataById(String id) {
            // In real scenario, this would fetch specific data
            return "Real data for ID: " + id;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Mockito Exercise 1 Demonstration ===\n");
        
        // Create service with real implementation
        ExternalApi realApi = new RealExternalApi();
        MyService service = new MyService(realApi);
        
        System.out.println("1. Using Real External API:");
        System.out.println("   fetchData(): " + service.fetchData());
        System.out.println("   fetchDataById('123'): " + service.fetchDataById("123"));
        System.out.println("   processData(): " + service.processData());
        
        System.out.println("\n2. In the test class (MyServiceTest.java), we:");
        System.out.println("   - Create a MOCK of ExternalApi instead of using the real one");
        System.out.println("   - STUB the mock methods to return predefined values");
        System.out.println("   - VERIFY that the service calls the expected methods");
        
        System.out.println("\n3. Benefits of mocking:");
        System.out.println("   - Tests run faster (no real API calls)");
        System.out.println("   - Tests are reliable (no network dependencies)");
        System.out.println("   - Can test error scenarios easily");
        System.out.println("   - Can verify exact interactions");
        
        System.out.println("\n4. To run the actual tests:");
        System.out.println("   - Use: mvn test");
        System.out.println("   - Or run MyServiceTest.java in your IDE");
        
        System.out.println("\n=== End Demonstration ===");
    }
}
