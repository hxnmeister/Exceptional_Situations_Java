package com.ua.project.exceptions;

public class NotEnoughMoneyOnBalanceException extends RuntimeException {
    public NotEnoughMoneyOnBalanceException(String message) {
        super(message);
    }
    public NotEnoughMoneyOnBalanceException() {
        this("Not enough money on balance!");
    }
}
