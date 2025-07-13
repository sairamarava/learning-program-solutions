package com.example.service;

import com.example.entity.City;
import com.example.entity.Country;
import com.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country createCountryWithCities() {
        Country country = new Country();
        country.setName("France");
        country.setCountryCode("FR");

        City city1 = new City();
        city1.setName("Paris");
        city1.setCountry(country);

        City city2 = new City();
        city2.setName("Lyon");
        city2.setCountry(country);

        country.setCities(Arrays.asList(city1, city2));

        return countryRepository.save(country);
    }
}
