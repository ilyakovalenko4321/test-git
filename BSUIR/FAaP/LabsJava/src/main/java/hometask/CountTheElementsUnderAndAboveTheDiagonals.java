package main.java.hometask;



public class CountTheElementsUnderAndAboveTheDiagonals {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println("Сумма над главной диагональю: " + sumAboveMainDiagonal(matrix));
        System.out.println("Сумма под главной диагональю: " + sumBelowMainDiagonal(matrix));
        System.out.println("Сумма над побочной диагональю: " + sumAboveSecondaryDiagonal(matrix));
        System.out.println("Сумма под побочной диагональю: " + sumBelowSecondaryDiagonal(matrix));
    }

    public static int sumAboveMainDiagonal(int[][] matrix) {
        int sum = 0;
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public static int sumBelowMainDiagonal(int[][] matrix) {
        int sum = 0;
        int size = matrix.length;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public static int sumAboveSecondaryDiagonal(int[][] matrix) {
        int sum = 0;
        int size = matrix.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public static int sumBelowSecondaryDiagonal(int[][] matrix) {
        int sum = 0;
        int size = matrix.length;
        for (int i = 1; i < size; i++) {
            for (int j = size - i; j < size; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}
