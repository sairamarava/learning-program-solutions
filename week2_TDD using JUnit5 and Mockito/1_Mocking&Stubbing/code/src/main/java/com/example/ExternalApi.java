package com.example;

/**
 * External API interface that we want to mock in our tests.
 * This represents a dependency that our service relies on.
 */
public interface ExternalApi {
    
    /**
     * Simulates fetching data from an external API.
     * @return String data from the external source
     */
    String getData();
    
    /**
     * Simulates checking if the external API is available.
     * @return true if the API is available, false otherwise
     */
    boolean isAvailable();
    
    /**
     * Simulates fetching data with a specific parameter.
     * @param id the identifier for the data to fetch
     * @return String data corresponding to the given id
     */
    String getDataById(String id);
}
