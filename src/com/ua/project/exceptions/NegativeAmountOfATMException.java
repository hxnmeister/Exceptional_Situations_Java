package com.ua.project.exceptions;

public class NegativeAmountOfATMException extends RuntimeException{
    public NegativeAmountOfATMException(String message){
        super(message);
    }
    public NegativeAmountOfATMException(){
        this("Amount of ATM cannot receive negative value!");
    }
}
