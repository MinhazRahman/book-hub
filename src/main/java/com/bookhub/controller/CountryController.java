package com.bookhub.controller;

import com.bookhub.model.Country;
import com.bookhub.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bookhub")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getCountries(){
        return countryService.findAll();
    }

    @GetMapping("/countries/{countryId}")
    public Country getCountry(@PathVariable(name = "countryId") Integer countryId){
        Country country= countryService.findById(countryId);

        if (country == null){
            throw new RuntimeException("Country not found with id - " + countryId);
        }
        return country;
    }

}
