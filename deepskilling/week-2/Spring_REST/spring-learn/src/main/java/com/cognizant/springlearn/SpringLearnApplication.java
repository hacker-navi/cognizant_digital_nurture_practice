package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Spring Boot Application class for Spring REST.
 *
 * @SpringBootApplication is a convenience annotation that combines:
 *   - @Configuration      : marks class as source of bean definitions
 *   - @EnableAutoConfiguration : tells Spring Boot to auto-configure based on classpath
 *   - @ComponentScan      : scans com.cognizant.springlearn for Spring components
 */
@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("Inside main");

        // Demonstrate Spring XML ApplicationContext
        displayCountry();
    }

    /**
     * Reads the country bean from Spring XML configuration file and displays details.
     *
     * ClassPathXmlApplicationContext loads beans from a classpath-relative XML file.
     * context.getBean("country", Country.class) retrieves the bean by ID.
     */
    private static void displayCountry() {
        LOGGER.info("Start displayCountry");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());
        LOGGER.info("End displayCountry");
    }
}
