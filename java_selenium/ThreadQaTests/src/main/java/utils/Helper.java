package utils;

import java.util.Random;

public class Helper {

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(index));
        }
        return stringBuilder.toString();
    }

    // Генерация случайного email
    public static String generateRandomEmail() {
        return generateRandomString(10) + "@example.com";
    }

    public static int generateRandomNumber(int numberOfDigits) {
        Random random = new Random();
        int min = (int) Math.pow(10, numberOfDigits - 1);
        int max = (int) Math.pow(10, numberOfDigits) - 1;
        return random.nextInt(max - min + 1) + min;
    }

    // Генерация случайного адреса
    public static String generateRandomAddress() {
        return generateRandomString(20) + " " + generateRandomString(10) + " St.";
    }
}
