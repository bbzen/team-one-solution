package ru.practikum.teamonesolution.service;

public class Decoder {
    public static String decode(String encoded, int offset) {
        StringBuilder decoded = new StringBuilder();

        for (int i = 0; i < encoded.length(); i++) {
            char currentChar = encoded.charAt(i);

            // Проверяем, является ли символ буквой английского алфавита
            if (Character.isLetter(currentChar)) {
                // Определяем тип буквы (заглавная или строчная)
                boolean isUpperCase = Character.isUpperCase(currentChar);
                char base = isUpperCase ? 'A' : 'a';

                // Расшифровываем символ с учетом сдвига
                int decodedChar = (currentChar - base - offset + 26) % 26 + base;

                // Добавляем расшифрованный символ к результату
                decoded.append((char) decodedChar);
            } else {
                // Если символ не является буквой, оставляем его без изменений
                decoded.append(currentChar);
            }
        }

        return decoded.toString();
    }

}
