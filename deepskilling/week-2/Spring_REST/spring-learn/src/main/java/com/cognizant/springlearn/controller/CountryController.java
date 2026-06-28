package com.cognizant.springlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;

/**
 * REST Controller for Country endpoints.
 *
 * Spring Boot auto-serializes the returned Country object to JSON
 * using Jackson (included with spring-boot-starter-web).
 * The Content-Type response header will be "application/json".
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    // ─────────────────────────────────────────────────────────────────────────
    // GET INDIA (from XML bean)
    // URL: GET http://localhost:8083/country
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Returns India country details loaded from Spring XML configuration.
     *
     * How it works:
     *  1. Loads ClassPathXmlApplicationContext with country.xml
     *  2. Retrieves the "country" bean (configured as India)
     *  3. Returns Country object — Jackson converts it to JSON automatically
     *
     * Sample Response: {"code":"IN","name":"India"}
     */
    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("country={}", country);
        LOGGER.info("End");
        return country;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // GET COUNTRY BY CODE (case insensitive)
    // URL: GET http://localhost:8083/countries/{code}
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Returns a specific country based on the country code (case insensitive).
     *
     * @PathVariable extracts {code} from the URL path.
     * The service iterates through the country list and matches case-insensitively.
     *
     * Sample Request:  GET http://localhost:8083/countries/in
     * Sample Response: {"code":"IN","name":"India"}
     */
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.debug("country={}", country);
        LOGGER.info("End");
        return country;
    }
}
