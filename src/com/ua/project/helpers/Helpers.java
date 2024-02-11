package com.ua.project.helpers;

import com.ua.project.exceptions.NegativeNumberException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Helpers {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getPositiveIntegerInput(final String inputMessage) {
        int tempInteger;

        while(true){
            try{
                System.out.print(inputMessage);
                tempInteger = SCANNER.nextInt();

                if(tempInteger < 0){
                    throw new NegativeNumberException();
                }

                return tempInteger;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                SCANNER.next();
            }
        }
    }
}
