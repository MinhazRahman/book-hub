package com.bookhub.service;

import com.bookhub.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Integer id);
    Country save(Country country);
    Country update(Integer id, Country country);
    void deleteById(Integer id);
}
