package com.ua.project.main;

import com.ua.project.ATM;
import com.ua.project.Bank;
import com.ua.project.Banknote;
import com.ua.project.exceptions.NegativeAmountOfATMException;

public class Main {
    public static void main(String[] args) {
        try {
            ATM atm = new ATM(500, 5, new Banknote[]{
                    new Banknote(4, 100),
                    new Banknote(3, 20),
                    new Banknote(1, 500)});

//            ATM atm = new ATM();

//            atm.initialize();
            System.out.println(atm);
            System.out.println("Balance: " + atm.getCurrentBalance());

            atm.withdrawMoney(800);
            System.out.println(atm);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
