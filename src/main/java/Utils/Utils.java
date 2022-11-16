package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {
    public static void printMenu() {
        System.out.println("-".repeat(25));
        System.out.println("Welcome to Swedbank!");
        System.out.println("Enter the number of a menu option below:");
        System.out.println("1. See available authentication methods for a specific account");
        System.out.println("2. Initiate an authentication session for a specific social security number");
        System.out.println("0. Exit application");
        System.out.println("-".repeat(25));
    }


    public static int userInputInteger() {
        Scanner userInput = new Scanner(System.in);
        int inputNumber;
        try {
            inputNumber = userInput.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a valid number.");
            inputNumber = userInputInteger();
        }
        return inputNumber;
    }

    public static long userInputLong() {
        Scanner userInput = new Scanner(System.in);
        long inputNumber;
        try {
            inputNumber = userInput.nextLong();
        } catch (Exception e) {
            System.out.println("Please enter a valid number.");
            inputNumber = userInputLong();
        }
        return inputNumber;
    }

    public static String userInputString() {
        Scanner userInput = new Scanner(System.in);
        String input = "";
        try {
            input = userInput.next();
        } catch (Exception e) {
            System.out.println("Please enter a valid String.");
            userInputString();
        }
        return input;
    }

    public static long getSocialSecurityInput() {
        boolean validSSN = false;
        long ssn;
        do {
            ssn = Utils.userInputLong();

            // TODO: Add validation of last 4 digits
            if (!(String.valueOf(ssn).length() == 12)) {
                System.out.println("Invalid input: Not 12 characters. Enter social security number:");
            }  else if (!isValidDate(ssn)) {
                System.out.println("Invalid input: Not a valid date. Enter social security number:");
            } else if(!isBorn(ssn)) {
                System.out.println("Invalid input: Not born yet. Enter social security number:");
            } else if(isOver120YearsOld(ssn)) {
                System.out.println("Invalid input: Person over 120 years. Enter social security number:");
            } else {
                validSSN = true;
            }

        } while (!validSSN);

        return ssn;
    }

    private static boolean isValidDate(long ssn) {
        String birthday = String.valueOf(ssn).substring(0,8);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(birthday.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private static boolean isBorn(long ssn) {
        String birthday = String.valueOf(ssn).substring(0,8);
        try {
            return LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyMMdd")).isBefore(LocalDate.now());
        } catch(Exception e) {
            System.out.println("Unable to parse date. Assume not yet born.");
            return false;
        }
    }

    private static boolean isOver120YearsOld(long ssn) {
        try {
            return LocalDate.parse(String.valueOf(ssn).substring(0,8), DateTimeFormatter.ofPattern("yyyyMMdd"))
                    .isBefore(LocalDate.now()
                            .minusYears(120));
        } catch (Exception e) {
            System.out.println("Unable to parse date.");
            return true;
        }
    }
}
