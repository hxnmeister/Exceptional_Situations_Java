package com.ua.project;

import com.ua.project.exceptions.*;
import com.ua.project.helpers.Helpers;

import java.util.Arrays;
import java.util.Comparator;

public class ATM {
    private int minWithdrawalAmount;
    private int maxBanknotesToDispense;
    private Banknote[] allowedBanknotes;

    public ATM(int minWithdrawalAmount, int maxBanknotesToDispense, Banknote[] allowedBanknotes) {
        for (Banknote allowedBanknote : allowedBanknotes) {
            if(!isValidDenomination(allowedBanknote.getDenomination())){
                throw new InvalidDenominationException();
            }
        }

        this.allowedBanknotes = allowedBanknotes;
        this.minWithdrawalAmount = minWithdrawalAmount;
        this.maxBanknotesToDispense = maxBanknotesToDispense;
    }
    public ATM(){
        this(1, 1, new Banknote[0]);
    }

    public int getMinWithdrawalAmount() {
        return minWithdrawalAmount;
    }

    public void setMinWithdrawalAmount(int minWithdrawalAmount) {
        this.minWithdrawalAmount = minWithdrawalAmount;
    }

    public int getMaxBanknotesToDispense() {
        return maxBanknotesToDispense;
    }

    public void setMaxBanknotesToDispense(int maxBanknotesToDispense) {
        this.maxBanknotesToDispense = maxBanknotesToDispense;
    }

    public Banknote[] getAllowedBanknotes() {
        return allowedBanknotes;
    }

    private boolean isValidDenomination(int denomination) {
        for (int allowedDenomination : Banknote.getAllowedDenominations()) {
            if (allowedDenomination == denomination) {
                return true;
            }
        }
        return false;
    }

    private boolean isDenominationInArray(int denomination) {
        for (Banknote allowedBanknote : this.getAllowedBanknotes()) {
            if(allowedBanknote.getDenomination() == denomination){
                return true;
            }
        }

        return false;
    }

    private static Banknote[] addABanknote(Banknote[] banknotes, Banknote newAllowedBanknote) {
        Banknote[] tempArray = Arrays.copyOf(banknotes, banknotes.length + 1);
        tempArray[tempArray.length - 1] = newAllowedBanknote;

        return tempArray;
    }

    public void initialize() {
        int tempAllowedDenomination;
        int tempAmountOfAllowedDenominations;

        minWithdrawalAmount = Helpers.getPositiveIntegerInput("\n Enter minimal withdrawal amount: ");
        maxBanknotesToDispense = Helpers.getPositiveIntegerInput("\n Enter maximum of banknotes to dispense: ");

        System.out.println("\n In fields below enter allowed banknotes (enter \"0\" to complete input):");
        while(true) {
            tempAllowedDenomination = Helpers.getPositiveIntegerInput("\n Input field for allowed denomination: ");
            if(tempAllowedDenomination == 0){
                break;
            }
            else if(!isValidDenomination(tempAllowedDenomination)){
                System.out.print(" Denomination is not in allowed range ( ");
                for (int allowedDenomination : Banknote.getAllowedDenominations()) {
                    System.out.print(allowedDenomination + " ");
                }
                System.out.println(")");

                continue;
            }
            else if(isDenominationInArray(tempAllowedDenomination)) {
                System.out.println(" Such denomination is already exists!");

                continue;
            }

            tempAmountOfAllowedDenominations = Helpers.getPositiveIntegerInput("\n Input field for amount of allowed denominations: ");
            if(tempAmountOfAllowedDenominations == 0){
                break;
            }

            this.allowedBanknotes = ATM.addABanknote(this.allowedBanknotes, new Banknote(tempAmountOfAllowedDenominations, tempAllowedDenomination));
        }
    }

    public int getCurrentBalance() {
        int balance = 0;

        for (Banknote allowedBanknote : this.getAllowedBanknotes()) {
            balance += (allowedBanknote.getDenomination() * allowedBanknote.getAmount());
        }

        return balance;
    }

    public Banknote[] withdrawMoney(int withdrawSum) {
        if(getCurrentBalance() < withdrawSum){
            throw new NotEnoughMoneyOnBalanceException();
        }
        else if(withdrawSum < this.getMinWithdrawalAmount()){
            throw new SmallWithdrawSumException();
        }

        int issuedBanknotes = 0;
        Banknote[] withdrewBanknotes = new Banknote[0];
        Banknote[] copyOfAllowedBanknotes = Arrays.copyOf(this.allowedBanknotes, this.allowedBanknotes.length);

        try {
            Arrays.sort(copyOfAllowedBanknotes, Comparator.comparingInt(Banknote::getDenomination).reversed());

            for (Banknote allowedBanknote : copyOfAllowedBanknotes) {
                int numOfNotesToWithdraw = Math.min(withdrawSum / allowedBanknote.getDenomination(), allowedBanknote.getAmount());

                if(numOfNotesToWithdraw > 0){
                    issuedBanknotes += numOfNotesToWithdraw;

                    if(issuedBanknotes <= this.getMaxBanknotesToDispense()){
                        withdrewBanknotes = ATM.addABanknote(withdrewBanknotes, new Banknote(numOfNotesToWithdraw, allowedBanknote.getDenomination()));
                        withdrawSum -= (allowedBanknote.getDenomination() * numOfNotesToWithdraw);
                        allowedBanknote.setAmount(allowedBanknote.getAmount() - numOfNotesToWithdraw);
                    }
                    else{
                        throw new BeyondMaxLimitException();
                    }
                }
                else if(withdrawSum == 0){
                    this.allowedBanknotes = copyOfAllowedBanknotes;
                    return withdrewBanknotes;
                }
            }

            if(withdrawSum != 0){
                throw new NotEnoughBanknotesException();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return new Banknote[0];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(" Min withdrawal amount: " + this.getMinWithdrawalAmount() + "\n Max banknotes to dispense: " + this.getMaxBanknotesToDispense() + "\n Allowed banknotes: ");

        for (Banknote allowedBanknote : this.getAllowedBanknotes()) {
            builder.append(allowedBanknote.getDenomination()).append("(").append(allowedBanknote.getAmount()).append(") ");
        }

        return builder.toString();
    }
}
