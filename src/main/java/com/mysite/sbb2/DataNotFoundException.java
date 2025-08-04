package com.mysite.sbb2;

/**
 * Exception thrown when an entity cannot be located in the database.
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
