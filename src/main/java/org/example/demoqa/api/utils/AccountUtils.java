package org.example.demoqa.api.utils;
import lombok.Data;

import java.util.Random;


public class AccountUtils {
    public class PasswordGenerator {

        // Наборы символов
        private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String DIGITS = "0123456789";
        private static final String SPECIALS = "@#$%^&*";
        //();:,.[]={}+-|_<>!?

        // Общий набор символов для генерации пароля
        private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIALS;

        // Минимальная длина пароля
        private static final int MIN_LENGTH = 8;

        // Генератор случайных чисел
        private static final Random random = new Random();

        public static String generatePassword() {
            StringBuilder password = new StringBuilder();

            // Добавляем хотя бы один символ из каждой категории
            password.append(getRandomChar(LOWERCASE)); // Маленькая буква
            password.append(getRandomChar(UPPERCASE)); // Заглавная буква
            password.append(getRandomChar(DIGITS));    // Цифра
            password.append(getRandomChar(SPECIALS));  // Специальный символ

            // Добавляем оставшиеся символы
            for (int i = password.length(); i < MIN_LENGTH; i++) {
                password.append(getRandomChar(ALL_CHARS));
            }

            // Перемешиваем символы, чтобы порядок был случайным
            return shuffleString(password.toString());
        }

        // Метод для получения случайного символа из строки
        private static char getRandomChar(String charSet) {
            int randomIndex = random.nextInt(charSet.length());
            return charSet.charAt(randomIndex);
        }

        // Метод для перемешивания символов в строке
        private static String shuffleString(String input) {
            char[] characters = input.toCharArray();
            for (int i = 0; i < characters.length; i++) {
                int randomIndex = random.nextInt(characters.length);
                char temp = characters[i];
                characters[i] = characters[randomIndex];
                characters[randomIndex] = temp;
            }
            return new String(characters);
        }


    }
}
