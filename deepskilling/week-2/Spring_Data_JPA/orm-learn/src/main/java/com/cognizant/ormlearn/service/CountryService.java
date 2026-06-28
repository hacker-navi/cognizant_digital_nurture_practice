package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

/**
 * Service class for Country entity.
 *
 * Provides business logic for managing country data.
 * All methods are annotated with @Transactional to ensure proper
 * transaction management via Spring Data JPA / Hibernate.
 */
@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // ─────────────────────────────────────────────────────────────────────────
    // GET ALL COUNTRIES
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Retrieves all countries from the database.
     *
     * @return list of all countries
     */
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // GET COUNTRY BY CODE
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Finds a country by its 2-character country code.
     *
     * @param code the country code (e.g., "IN")
     * @return the matching Country
     * @throws CountryNotFoundException if no country found with the given code
     */
    @Transactional
    public Country getCountry(String code) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(code.toUpperCase());
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + code);
        }
        return result.get();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ADD COUNTRY
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Adds a new country to the database.
     *
     * @param country the Country object to persist
     */
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // UPDATE COUNTRY
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Updates an existing country in the database.
     * Uses save() which performs an UPDATE when the entity's @Id already exists.
     *
     * @param country the Country with updated values
     */
    @Transactional
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // DELETE COUNTRY
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Deletes a country by its country code.
     *
     * @param code the 2-character code of the country to delete
     */
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code.toUpperCase());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FIND BY PARTIAL NAME
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Finds all countries whose name contains the given partial string (case insensitive).
     *
     * @param partialName substring to search within country names
     * @return list of matching countries
     */
    @Transactional
    public List<Country> getCountriesByPartialName(String partialName) {
        return countryRepository.findByNameContainingIgnoreCase(partialName);
    }
}
