package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class demonstrating Mockito mocking and stubbing techniques.
 * This class tests MyService by mocking its ExternalApi dependency.
 */
public class MyServiceTest {

    @Mock
    private ExternalApi mockApi;
    
    private MyService service;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        // Create service with mocked dependency
        service = new MyService(mockApi);
    }

    /**
     * Test 1: Basic mocking and stubbing as specified in the exercise
     */
    @Test
    public void testExternalApi() {
        // Create a mock object for the external API (alternative approach)
        ExternalApi mockApi = mock(ExternalApi.class);
        
        // Stub the methods to return predefined values
        when(mockApi.getData()).thenReturn("Mock Data");
        when(mockApi.isAvailable()).thenReturn(true);
        
        // Create service with mock
        MyService service = new MyService(mockApi);
        
        // Test the service
        String result = service.fetchData();
        
        // Verify the result
        assertEquals("Mock Data", result);
        
        // Verify that the mock methods were called
        verify(mockApi).isAvailable();
        verify(mockApi).getData();
    }

    /**
     * Test 2: Testing with mock returning false for availability
     */
    @Test
    void testFetchDataWhenServiceUnavailable() {
        // Arrange - stub the mock to return false for availability
        when(mockApi.isAvailable()).thenReturn(false);
        
        // Act
        String result = service.fetchData();
        
        // Assert
        assertEquals("Service unavailable", result);
        
        // Verify that isAvailable was called but getData was not called
        verify(mockApi).isAvailable();
        verify(mockApi, never()).getData();
    }

    /**
     * Test 3: Testing with parametrized method
     */
    @Test
    void testFetchDataById() {
        // Arrange
        String testId = "123";
        String expectedData = "Data for ID: 123";
        
        when(mockApi.isAvailable()).thenReturn(true);
        when(mockApi.getDataById(testId)).thenReturn(expectedData);
        
        // Act
        String result = service.fetchDataById(testId);
        
        // Assert
        assertEquals(expectedData, result);
        
        // Verify method calls
        verify(mockApi).isAvailable();
        verify(mockApi).getDataById(testId);
    }

    /**
     * Test 4: Testing with invalid input
     */
    @Test
    void testFetchDataByIdWithInvalidInput() {
        // Act & Assert for null ID
        String result1 = service.fetchDataById(null);
        assertEquals("Invalid ID", result1);
        
        // Act & Assert for empty ID
        String result2 = service.fetchDataById("");
        assertEquals("Invalid ID", result2);
        
        // Act & Assert for whitespace ID
        String result3 = service.fetchDataById("   ");
        assertEquals("Invalid ID", result3);
        
        // Verify that mock methods were never called for invalid inputs
        verify(mockApi, never()).isAvailable();
        verify(mockApi, never()).getDataById(anyString());
    }

    /**
     * Test 5: Testing data processing functionality
     */
    @Test
    void testProcessData() {
        // Arrange
        when(mockApi.isAvailable()).thenReturn(true);
        when(mockApi.getData()).thenReturn("Raw Data");
        
        // Act
        String result = service.processData();
        
        // Assert
        assertEquals("Processed: Raw Data", result);
        
        // Verify interactions
        verify(mockApi).isAvailable();
        verify(mockApi).getData();
    }

    /**
     * Test 6: Testing process data when service is unavailable
     */
    @Test
    void testProcessDataWhenServiceUnavailable() {
        // Arrange
        when(mockApi.isAvailable()).thenReturn(false);
        
        // Act
        String result = service.processData();
        
        // Assert
        assertEquals("Service unavailable", result);
        
        // Verify only isAvailable was called
        verify(mockApi).isAvailable();
        verify(mockApi, never()).getData();
    }

    /**
     * Test 7: Demonstrating argument matchers
     */
    @Test
    void testWithArgumentMatchers() {
        // Arrange - using argument matchers
        when(mockApi.isAvailable()).thenReturn(true);
        when(mockApi.getDataById(anyString())).thenReturn("Generic Data");
        
        // Act
        String result1 = service.fetchDataById("any-id");
        String result2 = service.fetchDataById("another-id");
        
        // Assert
        assertEquals("Generic Data", result1);
        assertEquals("Generic Data", result2);
        
        // Verify method was called twice with any string
        verify(mockApi, times(2)).isAvailable();
        verify(mockApi, times(2)).getDataById(anyString());
    }

    /**
     * Test 8: Demonstrating multiple stubbing scenarios
     */
    @Test
    void testMultipleStubbing() {
        // Arrange - different behaviors for different inputs
        when(mockApi.isAvailable()).thenReturn(true);
        when(mockApi.getDataById("user1")).thenReturn("User 1 Data");
        when(mockApi.getDataById("user2")).thenReturn("User 2 Data");
        when(mockApi.getDataById("invalid")).thenReturn(null);
        
        // Act & Assert
        assertEquals("User 1 Data", service.fetchDataById("user1"));
        assertEquals("User 2 Data", service.fetchDataById("user2"));
        assertNull(service.fetchDataById("invalid"));
        
        // Verify specific method calls
        verify(mockApi, times(3)).isAvailable();
        verify(mockApi).getDataById("user1");
        verify(mockApi).getDataById("user2");
        verify(mockApi).getDataById("invalid");
    }
}
