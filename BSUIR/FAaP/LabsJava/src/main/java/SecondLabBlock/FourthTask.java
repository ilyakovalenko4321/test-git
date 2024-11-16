package main.java.SecondLabBlock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FourthTask {

    public static String fileValidator() {
        Scanner scanner = new Scanner(System.in);
        String fileName = "";
        boolean fileIsNotValid = true;
        while (fileIsNotValid) {
            System.out.print("Enter the file path: ");
            fileName = scanner.nextLine();
            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) {
                fileIsNotValid = false;
            } else {
                System.out.println("File does not exist. Please try again.");
            }
        }
        return fileName;
    }

    public static int[][] contentValidator(File file) {
        int[][] matrix = null;
        boolean inputIsValid = false;
        Scanner arraySize = new Scanner(System.in);
        while (!inputIsValid) {
            try (Scanner fileScanner = new Scanner(file)) {
                System.out.print("Enter the dimension size for the square matrix: ");
                int dimension = arraySize.nextInt();
                matrix = new int[dimension][dimension];
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        if (fileScanner.hasNextInt()) {
                            matrix[i][j] = fileScanner.nextInt();
                        } else {
                            throw new InputMismatchException("Invalid input in file.");
                        }
                    }
                }
                inputIsValid = true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                file = new File(fileValidator());
            } catch (InputMismatchException e) {
                System.out.println("File content is invalid: " + e.getMessage());
                file = new File(fileValidator());
            }
        }
        return matrix;
    }

    public static int[][] inputMethod(Scanner scanner) {
        int[][] inputArray = null;
        boolean inputIsValid = false;

        while (!inputIsValid) {
            System.out.print("Choose input method (file/console): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("file")) {
                String fileName = fileValidator();
                inputArray = contentValidator(new File(fileName));
                inputIsValid = true;
            } else if (choice.equalsIgnoreCase("console")) {
                try {
                    System.out.print("Enter the dimension size for the square matrix: ");
                    int dimension = scanner.nextInt();
                    inputArray = new int[dimension][dimension];
                    System.out.println("Enter the matrix values:");
                    for (int i = 0; i < dimension; i++) {
                        for (int j = 0; j < dimension; j++) {
                            inputArray[i][j] = scanner.nextInt();
                        }
                    }
                    inputIsValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter integers.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } else {
                System.out.println("Invalid input method. Please choose either 'file' or 'console'.");
            }
        }
        return inputArray;
    }

    public static int workingFunction(int[][] matrix) {
        int min, max, sum;
        min = 0;
        max = matrix.length - 1;
        sum = 0;

        int row;
        row = 0;
        while (min <= max) {
            for (int i = min; i <= max; i++) {
                sum += matrix[row][i];
            }
            min++;
            max--;
            row++;
        }

        max++;
        min--;

        if (max - min == 0) {
            max++;
            min--;
        }

        while (min >= 0) {
            for (int i = min; i <= max; i++) {
                sum += matrix[row][i];
            }
            max++;
            min--;
            row++;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = inputMethod(scanner);
        System.out.println("Sum of selected matrix elements: " + workingFunction(matrix));
    }
}
