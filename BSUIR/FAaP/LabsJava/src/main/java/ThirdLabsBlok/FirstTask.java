package main.java.ThirdLabsBlok;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FirstTask {

    private static final Set<Character> allowedCharacters;
    private static final Set<Character> resultAllowedCharacters;

    static {
        allowedCharacters = new HashSet<Character>();
        allowedCharacters.add('2');
        allowedCharacters.add('4');
        allowedCharacters.add('6');
        allowedCharacters.add('8');
        allowedCharacters.add('{');
        allowedCharacters.add('}');
        allowedCharacters.add('[');
        allowedCharacters.add(']');
        allowedCharacters.add('(');
        allowedCharacters.add(')');
        allowedCharacters.add('+');
        allowedCharacters.add('-');
        allowedCharacters.add('*');
        allowedCharacters.add('/');
    }

    static {
        resultAllowedCharacters = new HashSet<>();
    }

    private static String fileContentReader(File file) {

        StringBuilder result;
        result = new StringBuilder();
        try {
            Scanner fileContentScanner;
            fileContentScanner = new Scanner(file);

            while(fileContentScanner.hasNextLine()){
                result.append(fileContentScanner.nextLine());
            }

        } catch (FileNotFoundException ignored) {}
        return result.toString();
    }

    private static File openFile() {

        boolean fileIsntValid;
        fileIsntValid = true;
        File file;
        file = new File("");

        while (fileIsntValid) {

            System.out.println("Введите путь к файлу, который хотите прочитать");
            Scanner pathScanner;
            pathScanner = new Scanner(System.in);

            String requirePath;
            requirePath = pathScanner.nextLine();

            file = new File(requirePath);

            int initial = requirePath.length() - 4;
            StringBuilder fileExtension;
            fileExtension = new StringBuilder();
            for (int i = initial; i < requirePath.length(); i++) {
                fileExtension.append(requirePath.charAt(i));
            }

            if (file.exists() && file.canRead() && fileExtension.toString().equals(".txt")) {
                fileIsntValid = false;
            } else {
                System.out.println("Не соблюдено одно или несколько условий: Файл существует. Файл открыт для чтения." +
                        "Расширение файла - .txt");
            }
        }
        return file;
    }


    private static void workingFunction(String nativeString) {
        for (Character symbol : nativeString.toCharArray()) {
            if (allowedCharacters.contains(symbol)) {
                resultAllowedCharacters.add(symbol);
            }
        }
    }

    //ToDO: Function to start
    private static void initializer() {
        System.out.println("Эта программа находит подстроку в вашей строке," +
                "которую можно интерпретировать как целое число");

        System.out.println("Введите console/file в зависимости от того, как вы хотите прочесть строку");

                Scanner inputTypeReader = new Scanner(System.in);

        String inputType = inputTypeReader.nextLine();

        while (!(inputType.equalsIgnoreCase("file") || inputType.equalsIgnoreCase("console"))) {
            System.out.println("Вы ввели неподдерживаемое нашей программой значение. Попробуйте заново");
                    System.out.println("console/file");
            inputType = inputTypeReader.nextLine();
        }


        String nativeString;
        if (inputType.equalsIgnoreCase("file")) {
            File file = openFile();
            nativeString = fileContentReader(file);
        } else {
            System.out.println("Введите исходную строку");
            Scanner nativeStringReader;
            nativeStringReader = new Scanner(System.in);
            nativeString = nativeStringReader.nextLine();
        }


        workingFunction(nativeString);
        for (Character resultAllowedCharacter : resultAllowedCharacters) {
            System.out.println(resultAllowedCharacter);
        }
    }

    public static void main(String[] args) {
        initializer();
    }


}
