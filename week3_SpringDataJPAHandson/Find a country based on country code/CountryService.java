package com.example.service;

import com.example.entity.Country;
import com.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    public Country getCountryByCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(Long id, Country countryDetails) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country != null) {
            country.setName(countryDetails.getName());
            country.setCapital(countryDetails.getCapital());
            country.setCountryCode(countryDetails.getCountryCode());
            return countryRepository.save(country);
        }
        return null;
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
