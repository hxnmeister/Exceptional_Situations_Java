package com.ua.project;

public class Banknote {
    private int amount;
    private int denomination;
    private final static int[] ALLOWED_DENOMINATIONS = {1, 2, 5, 10, 20, 50, 100, 200, 500};

    public Banknote(int amount, int denomination) {
        this.amount = amount;
        this.denomination = denomination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    //Возвращаю копию во избежания перезаписи значений массива
    public static int[] getAllowedDenominations() {
        return ALLOWED_DENOMINATIONS.clone();
    }
}
