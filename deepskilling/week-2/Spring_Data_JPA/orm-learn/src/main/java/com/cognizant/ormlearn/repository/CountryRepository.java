package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

/**
 * Repository interface for Country entity.
 *
 * Extends JpaRepository which provides built-in CRUD methods:
 *  - findAll(), findById(), save(), deleteById(), etc.
 *
 * Custom query methods follow Spring Data JPA naming conventions.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Finds all countries whose name contains the given string (case insensitive).
     *
     * Spring Data JPA auto-generates the query from the method name:
     * SELECT * FROM country WHERE LOWER(co_name) LIKE LOWER('%keyword%')
     *
     * @param name partial name to search
     * @return list of matching countries
     */
    List<Country> findByNameContainingIgnoreCase(String name);
}
