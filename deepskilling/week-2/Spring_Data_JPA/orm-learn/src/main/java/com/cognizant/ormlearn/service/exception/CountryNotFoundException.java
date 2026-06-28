package com.cognizant.ormlearn.service.exception;

/**
 * Custom exception thrown when a Country is not found in the database.
 */
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(String message) {
        super(message);
    }
}
