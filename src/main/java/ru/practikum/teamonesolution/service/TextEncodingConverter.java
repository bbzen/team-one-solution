package ru.practikum.teamonesolution.service;

import org.mozilla.universalchardet.UniversalDetector;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TextEncodingConverter {
    public static void main(String[] args) {
        String text = "ЇwџЇџ‹џЇ‹љџ‹љЇџ‹ww";

        // Определите кодировку текста
        String detectedCharset = detectCharset(text);

        // Преобразуйте текст в нужную кодировку (например, UTF-8)
        String convertedText = convertToCharset(text, Charset.forName("UTF-8"));

        // Вывод результатов
        System.out.println("Определенная кодировка: " + detectedCharset);
        System.out.println("Преобразованный текст: " + convertedText);
    }

    public static String detectCharset(String text) {
        UniversalDetector detector = new UniversalDetector(null);

        byte[] bytes = text.getBytes();
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();

        String detectedCharset = detector.getDetectedCharset();
        detector.reset();

        if (detectedCharset == null) {
            detectedCharset = StandardCharsets.UTF_8.name(); // Кодировка по умолчанию
        }

        return detectedCharset;
    }

    public static String convertToCharset(String text, Charset targetCharset) {
        String detectedCharset = detectCharset(text);

        try {
            byte[] bytes = text.getBytes(Charset.forName(detectedCharset));
            return new String(bytes, targetCharset);
        } catch (Exception e) {
            e.printStackTrace();
            return text; // В случае ошибки возвращаем исходный текст
        }
    }
}
