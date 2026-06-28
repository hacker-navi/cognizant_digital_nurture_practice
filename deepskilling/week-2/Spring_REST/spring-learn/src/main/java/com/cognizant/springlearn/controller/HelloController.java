package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Hello World endpoint.
 *
 * @RestController combines @Controller + @ResponseBody, so methods return
 * data directly as HTTP response body (no view resolution).
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    /**
     * Returns a "Hello World!!" greeting string.
     *
     * GET http://localhost:8083/hello
     *
     * @return plain text "Hello World!!"
     */
    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start");
        String response = "Hello World!!";
        LOGGER.info("End");
        return response;
    }
}
