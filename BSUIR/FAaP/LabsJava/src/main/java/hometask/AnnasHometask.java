package main.java.hometask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AnnasHometask {

    private final static String currentFileNameLocation = "resources/currentFileName.txt";

    private static void createFileToReturn(String outputFileName, String message) {
        try (FileWriter file = new FileWriter(outputFileName)) {
            file.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String workingFunction(String inputString) {
        char[] resultNewStringCharArray = new char[inputString.length()];
        char[] oldStringCharArray = inputString.toCharArray();
        for (int i = 0; i < inputString.length(); i++) {
            if ((i == inputString.length() - 1) && i % 2 == 0) {
                resultNewStringCharArray[i] = oldStringCharArray[i];
            } else if (i % 2 == 0) {
                resultNewStringCharArray[i + 1] = oldStringCharArray[i];
            } else {
                resultNewStringCharArray[i - 1] = oldStringCharArray[i];
            }
        }
        return new String(resultNewStringCharArray);
    }

    private static void renameFile(String newName) {
        File oldFile = getOldFile();
        String newFilePath = "resources/" + newName;
        if (!oldFile.renameTo(new File(newFilePath))) {
            throw new RuntimeException("Failed to rename file");
        }
        updateCurrentFileName(newName);
    }

    private static File getOldFile() {
        try {
            Scanner oldNameReader = new Scanner(new File(currentFileNameLocation));
            String oldFileName = oldNameReader.nextLine();
            return new File(oldFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateCurrentFileName(String newName) {
        try (FileWriter fileWriter = new FileWriter(currentFileNameLocation)) {
            fileWriter.write("resources/" + newName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter input file name: ");
        String newFileName = scanner.nextLine();
        String newFilePath = "resources/" + newFileName;

        renameFile(newFileName);

        String newFileContent;
        try (Scanner newFileContentReader = new Scanner(new File(newFilePath))) {
            newFileContent = newFileContentReader.nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String resultString = workingFunction(newFileContent);

        System.out.println("Enter name of output file: ");
        String outputFileName = "resources/" + scanner.nextLine();

        createFileToReturn(outputFileName, resultString);
    }
}
