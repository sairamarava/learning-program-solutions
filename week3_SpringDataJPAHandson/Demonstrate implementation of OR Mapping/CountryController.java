package com.example.controller;

import com.example.entity.Country;
import com.example.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/saveCountryWithCities")
    public Country saveCountryWithCities() {
        return countryService.createCountryWithCities();
    }
}
