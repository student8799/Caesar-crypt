package com.javarush.task.jdk13.task53.task5307;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CaesarCipher {
    private static final char[] ALFABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н',
            'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '«', '»', '"', ':', '!', '\'', '?', ' '};

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите путь к начальному файлу");
        String inputFileName = console.nextLine();
        System.out.println("Введите путь к готовому файлу");
        String outputFileName = console.nextLine();
        System.out.println("Введите ключ");
        int key = console.nextInt();
        System.out.println("Для шифрования введите 1, " +
                " для дешифровки введите 2 ," +
                " для выхода введите 0");

        int num = console.nextInt();
        switch (num) {
            case 1 :
                coder(inputFileName, outputFileName,key);
                System.out.println("файл зашифрован");
                break;
            case 2 :
                decoder(inputFileName, outputFileName, key);
                System.out.println("файл расшифрован");
                break;
            case 0 :
                break;
        }


    }

    public static void decoder(String input, String output, int key) {
        Path path = Path.of(input);
        if (Files.exists(path)) {
            try (FileReader reader = new FileReader(path.toFile());
                 FileWriter writer = new FileWriter(output)) {
                while (reader.ready()) {
                    int num = Character.toLowerCase(reader.read());
                    for (int i = 0; i < ALFABET.length; i++) {
                        if (num == ALFABET[i]) {
                            if (i == 0) {
                                i = ALFABET.length;
                            }
                            int decryptIndex = (i - key) % ALFABET.length;
                            writer.write(ALFABET[decryptIndex]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void coder(String input, String output, int key) {
        Path path = Path.of(input);
        if (Files.exists(path) && (key > 0 && key < ALFABET.length - 1)) {
            try (FileReader reader = new FileReader(path.toFile());
                 FileWriter writer = new FileWriter(output)) {
                while (reader.ready()) {
                    int num = Character.toLowerCase(reader.read());
                    for (int i = 0; i < ALFABET.length; i++) {
                        if (num == ALFABET[i]) {
                            int cryptIndex = (i + key) % ALFABET.length;
                            writer.write(ALFABET[cryptIndex]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
