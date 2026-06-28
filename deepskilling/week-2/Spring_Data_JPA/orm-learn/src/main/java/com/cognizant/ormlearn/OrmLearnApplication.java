package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

/**
 * Main Spring Boot Application class for ORM Learn.
 * Demonstrates Spring Data JPA with Hibernate and MySQL.
 */
@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);

        testGetAllCountries();
        testGetCountry();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testGetCountriesByPartialName();
    }

    /**
     * Test method to get all countries from the database.
     */
    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    /**
     * Test method to get a country by country code.
     */
    private static void testGetCountry() {
        LOGGER.info("Start");
        try {
            Country country = countryService.getCountry("IN");
            LOGGER.debug("country={}", country);
        } catch (Exception e) {
            LOGGER.error("Error fetching country: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    /**
     * Test method to add a new country.
     */
    private static void testAddCountry() {
        LOGGER.info("Start");
        Country newCountry = new Country();
        newCountry.setCode("TT");
        newCountry.setName("Test Territory");
        countryService.addCountry(newCountry);
        LOGGER.debug("New country added: {}", newCountry);
        LOGGER.info("End");
    }

    /**
     * Test method to update an existing country.
     */
    private static void testUpdateCountry() {
        LOGGER.info("Start");
        Country updateCountry = new Country();
        updateCountry.setCode("TT");
        updateCountry.setName("Test Territory Updated");
        countryService.updateCountry(updateCountry);
        LOGGER.debug("Country updated: {}", updateCountry);
        LOGGER.info("End");
    }

    /**
     * Test method to delete a country.
     */
    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("TT");
        LOGGER.debug("Country with code TT deleted.");
        LOGGER.info("End");
    }

    /**
     * Test method to find countries matching a partial name.
     */
    private static void testGetCountriesByPartialName() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getCountriesByPartialName("ind");
        LOGGER.debug("Matching countries={}", countries);
        LOGGER.info("End");
    }
}
