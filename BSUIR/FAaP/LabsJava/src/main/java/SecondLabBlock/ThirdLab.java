package main.java.SecondLabBlock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ThirdLab {

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
        Scanner arraySize;
        arraySize = new Scanner(System.in);
        while (!inputIsValid) {
            try (Scanner fileScanner = new Scanner(file)) {
                System.out.println("Введите количество строк");
                int rows = arraySize.nextInt();
                System.out.println("Введите количество столбцов");
                int cols = arraySize.nextInt();
                matrix = new int[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
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

    public static int[][] workingFunction(int[][] inputArray) {
        int rows = inputArray.length;
        int cols = inputArray[0].length + 2;
        int[][] outputArray = new int[rows][cols];

        // Добавляем нулевые столбцы в начало и конец каждой строки
        for (int i = 0; i < rows; i++) {
            outputArray[i][0] = 0; // Первый столбец нулевой
            outputArray[i][cols - 1] = 0; // Последний столбец нулевой
            for (int j = 0; j < inputArray[i].length; j++) {
                outputArray[i][j + 1] = inputArray[i][j]; // Сдвигаем остальные элементы
            }
        }

        return outputArray;
    }

    public static void fileRecord(int[][] outputArray, String fileName) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new File(fileName))) {
            for (int[] row : outputArray) {
                for (int value : row) {
                    writer.print(value + " ");
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void consoleRecorder(int[][] outputArray) {
        for (int[] row : outputArray) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static int[][] inputMethod(Scanner scanner) {
        int[][] inputArray = null;
        boolean inputIsValid = false;

        while (!inputIsValid) {
            System.out.println("Choose input method (file/console): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("file")) {
                String fileName = fileValidator();
                inputArray = contentValidator(new File(fileName));
                inputIsValid = true;
            } else if (choice.equalsIgnoreCase("console")) {
                try {
                    System.out.print("Enter number of rows: ");
                    int rows = scanner.nextInt();
                    System.out.print("Enter number of columns: ");
                    int cols = scanner.nextInt();
                    inputArray = new int[rows][cols];
                    System.out.println("Enter the matrix values:");
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] inputArray = inputMethod(scanner);
        int[][] outputArray = workingFunction(inputArray);

        // Определяем вывод на основе метода ввода
        System.out.println("Choose output method (file/console): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("file")) {
            String fileName = fileValidator();
            fileRecord(outputArray, fileName);
        } else if (choice.equalsIgnoreCase("console")) {
            consoleRecorder(outputArray);
        } else {
            System.out.println("Invalid input method.");

        }
    }
}
