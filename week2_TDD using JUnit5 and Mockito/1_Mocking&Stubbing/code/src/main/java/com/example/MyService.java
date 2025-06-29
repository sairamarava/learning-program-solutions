package com.example;

/**
 * Service class that depends on an external API.
 * This is the class we want to test by mocking its dependencies.
 */
public class MyService {
    
    private final ExternalApi externalApi;
    
    /**
     * Constructor injection of the external API dependency.
     * @param externalApi the external API implementation
     */
    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }
    
    /**
     * Fetches data using the external API.
     * @return the data fetched from the external API
     */
    public String fetchData() {
        if (externalApi.isAvailable()) {
            return externalApi.getData();
        } else {
            return "Service unavailable";
        }
    }
    
    /**
     * Fetches specific data by ID.
     * @param id the identifier for the data
     * @return the data corresponding to the given ID, or error message if not available
     */
    public String fetchDataById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return "Invalid ID";
        }
        
        if (externalApi.isAvailable()) {
            return externalApi.getDataById(id);
        } else {
            return "Service unavailable";
        }
    }
    
    /**
     * Processes data by fetching it and transforming it.
     * @return processed data with a prefix
     */
    public String processData() {
        String data = fetchData();
        if (!"Service unavailable".equals(data)) {
            return "Processed: " + data;
        }
        return data;
    }
}
