package com.ua.project.exceptions;

public class BeyondMaxLimitException extends RuntimeException{
    public BeyondMaxLimitException(String message){
        super(message);
    }
    public BeyondMaxLimitException() {
        this("Beyond maximum limit of banknotes!");
    }
}
