package com.ua.project.exceptions;

public class NotEnoughBanknotesException extends RuntimeException {
    public NotEnoughBanknotesException(String message){
        super(message);
    }
    public NotEnoughBanknotesException() {
        this("Not enough banknotes in ATM!");
    }
}
