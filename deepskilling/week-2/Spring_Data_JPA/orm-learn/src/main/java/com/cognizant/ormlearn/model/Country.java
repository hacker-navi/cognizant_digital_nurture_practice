package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class representing the 'country' table in the ormlearn database.
 *
 * Annotations:
 *  @Entity  - Marks this class as a JPA entity.
 *  @Table   - Maps this entity to the 'country' database table.
 *  @Id      - Marks the primary key field.
 *  @Column  - Maps fields to specific columns in the table.
 */
@Entity
@Table(name = "country")
public class Country {

    /** Primary key: 2-character country code (e.g., "IN") */
    @Id
    @Column(name = "co_code")
    private String code;

    /** Country name (e.g., "India") */
    @Column(name = "co_name")
    private String name;

    // ─── Getters & Setters ───────────────────────────────────────────────────

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ─── toString ────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
