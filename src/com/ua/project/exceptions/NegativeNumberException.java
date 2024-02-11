package com.ua.project.exceptions;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(String message) {
        super(message);
    }
    public NegativeNumberException() {
        this("Number cannot be negative!");
    }
}
