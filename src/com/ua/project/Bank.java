package com.ua.project;

import com.ua.project.exceptions.NegativeAmountOfATMException;

import java.util.Arrays;

public class Bank {
    private int amountOfATM;
    private ATM[] ATMs;

    public Bank(int amountOfATM) {
        if (amountOfATM < 0) {
            this.amountOfATM = 0;
            throw new NegativeAmountOfATMException("Cannot set amount of ATM to negative number!\n Value was set to zero!");
        }

        this.amountOfATM = amountOfATM;
        this.ATMs = new ATM[amountOfATM];
    }
    public Bank() {
        this(0);
    }

    public int getAmountOfATM() {
        return amountOfATM;
    }

    public void setAmountOfATM(int amountOfATM) {
        changeArraySizeOfATMs(amountOfATM);
        this.amountOfATM = amountOfATM;
    }

    private void changeArraySizeOfATMs(final int newAmountOfATM) {
        if (newAmountOfATM < 0) {
            throw new NegativeAmountOfATMException(" Amount of ATMs cannot be less than \"0\"!\n Value not changed!");
        }

        this.ATMs = Arrays.copyOf(this.ATMs, newAmountOfATM);
    }

}
