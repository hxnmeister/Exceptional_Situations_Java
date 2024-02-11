package com.ua.project.exceptions;

public class SmallWithdrawSumException extends RuntimeException {
    public SmallWithdrawSumException(String message) {
        super(message);
    }
    public SmallWithdrawSumException() {
        this("Entered less than min allowed withdraw sum!");
    }
}
