package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;

/**
 * Service class for Country operations.
 *
 * Loads country data from the Spring XML configuration (country.xml)
 * and provides methods to query the in-memory list.
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /**
     * Finds a country by its 2-character code (case insensitive).
     *
     * Implementation steps:
     *  1. Load the country list bean from country.xml
     *  2. Iterate through the list using a lambda expression
     *  3. Return the first country whose code matches (ignoring case)
     *
     * @param code the country code to search for (e.g., "in", "IN", "In")
     * @return matching Country object, or null if not found
     */
    @SuppressWarnings("unchecked")
    public Country getCountry(String code) {
        LOGGER.info("Start");

        // Load all countries from Spring XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");

        // Lambda-based case-insensitive matching
        Country result = countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        LOGGER.debug("Found country={}", result);
        LOGGER.info("End");
        return result;
    }
}
