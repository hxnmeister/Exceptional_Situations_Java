package com.ua.project.exceptions;

public class InvalidDenominationException extends RuntimeException{
    public InvalidDenominationException(String message){
        super(message);
    }
    public InvalidDenominationException(){
        this("Denominator is not in allowed range!");
    }
}
