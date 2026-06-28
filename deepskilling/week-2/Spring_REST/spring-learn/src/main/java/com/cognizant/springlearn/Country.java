package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * POJO class representing a Country.
 *
 * Configured as a Spring bean via country.xml.
 * Spring injects code and name via setter methods (property injection).
 */
public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    /** 2-character ISO country code (e.g., "IN") */
    private String code;

    /** Full country name (e.g., "India") */
    private String name;

    // ─── Constructor ─────────────────────────────────────────────────────────

    /**
     * Default no-arg constructor.
     * Spring calls this first when creating the bean, before injecting properties.
     */
    public Country() {
        LOGGER.debug("Inside Country Constructor");
    }

    // ─── Getters & Setters ───────────────────────────────────────────────────

    public String getCode() {
        LOGGER.debug("Inside getCode, code={}", code);
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("Inside setCode, code={}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("Inside getName, name={}", name);
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside setName, name={}", name);
        this.name = name;
    }

    // ─── toString ────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
