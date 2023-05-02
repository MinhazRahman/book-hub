package com.bookhub.service;

import com.bookhub.model.Country;
import com.bookhub.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    // inject CountryRepository using constructor injection
    private final CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Integer id) {
        return countryRepository.findById(id).orElseThrow();
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country update(Integer id, Country country) {
        Country existingCountry = countryRepository.findById(id).orElseThrow();

        existingCountry.setName(country.getName());
        existingCountry.setCode(country.getCode());
        // save the Book, if id == 0, then save/insert otherwise update
        return countryRepository.save(existingCountry);
    }

    @Override
    public void deleteById(Integer id) {
        countryRepository.deleteById(id);
    }
}
