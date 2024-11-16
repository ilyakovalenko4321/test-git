package main.java.hometask;

import java.util.Scanner;

public class LastDigitCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your number: ");
        int number = scanner.nextInt();

        System.out.print("Enter your stepen: ");
        int stepen = scanner.nextInt();

        int lastDigit = number % 10;

        if (stepen == 0) {
            System.out.println("Last digit: 1");
        } else {
            int tempNumber = lastDigit;
            for (int i = 1; i < stepen; i++) {
                lastDigit = lastDigit * tempNumber;
                lastDigit = lastDigit % 10;
            }
            System.out.println("Last digit: " + lastDigit);
        }

        scanner.close();
    }
}

