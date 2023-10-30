package org.example.utils;

import java.util.Scanner;

import static org.example.utils.AppScanner.getScanner;
import static org.example.utils.Utils.print;

public class AppInput {
    public static String enterString(String msg) {
        print(msg);
        return getScanner().nextLine();
    }

    public static double enterDouble(String msg){
        print(msg);
        Scanner scanner = getScanner();

        while (true) {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } catch (java.util.InputMismatchException e) {
                print("Invalid input. Please enter a valid double: ");
                scanner.next();
            }
        }
    }

    public static int enterInt(String msg) throws AppException {
        print(msg);
        int input;
        try {
            input = Integer.parseInt(getScanner().nextLine());
        } catch (Exception e) {
            throw new AppException(StringUtils.INVALID_CHOICE);
        }
        return input;
    }
}
