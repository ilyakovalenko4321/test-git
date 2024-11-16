package main.java.hometask;

import java.util.Arrays;
import java.util.Scanner;

public class QuadraticEquationsUsingIf {

    public static double a, b, c;

    public static double[] workingFunction() {
        Scanner coefficientReader = new Scanner(System.in);
        a = coefficientReader.nextDouble();
        b = coefficientReader.nextDouble();
        c = coefficientReader.nextDouble();

        // Adjust signs of coefficients if necessary
        if (a < 0) {
            a *= -1;
            b *= -1;
            c *= -1;
        } else if (b < 0) {
            b *= -1;
            c *= -1;
        }

        double[] roots = null; // initialize roots as null

        // Case handling
        if (a == 0 && b == 0 && c == 0) {
            System.out.println("Бесконечное множество решений");
        } else if (a == 0 && b != 0 && c != 0) {
            roots = new double[]{-c / b};
        } else if (a != 0 && b == 0 && c != 0) {
            if (c < 0) {
                roots = new double[]{Math.sqrt(-c / a)};
            } else {
                System.out.println("Не существует корней");
            }
        } else if (a != 0 && b != 0 && c == 0) {
            roots = new double[]{0, -b / (2 * a)};
        } else if (a != 0 && b == 0 && c == 0) {
            roots = new double[]{0};
        } else if (a == 0 && b != 0 && c == 0) {
            roots = new double[]{0};
        } else if (a == 0 && b == 0 && c != 0) {
            System.out.println("Нет решений");
        } else if (a != 0 && b != 0 && c != 0) {
            double D = b * b - 4 * a * c;
            if (D > 0) {
                roots = new double[]{
                        (-b + Math.sqrt(D)) / (2 * a),
                        (-b - Math.sqrt(D)) / (2 * a)
                };
            } else if (D == 0) {
                roots = new double[]{-b / (2 * a)};
            } else {
                System.out.println("Не существует корней");
            }
        }

        return roots;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(workingFunction()));
    }
}
