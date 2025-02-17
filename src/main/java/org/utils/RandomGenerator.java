package org.utils;

import java.util.Random;

public class RandomGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBER = "0123456789";
    private static final Random RANDOM = new Random();

    /**
     * Генерирует случайное слово, состоящее из букв и цифр.
     *
     * @param length Длина генерируемого слова.
     * @return Случайное слово.
     */
    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(index));
        }
        return randomString.toString();
    }

    /**
     * Генерирует случайное число в заданном диапазоне.
     *
     * @param length Длина генерируемого слова.
     * @return Случайное число.
     */
    public static String generateRandomNumber(int length) {
        StringBuilder randomLetters = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(NUMBER.length());
            randomLetters.append(NUMBER.charAt(index));
        }
        return randomLetters.toString();
    }
}