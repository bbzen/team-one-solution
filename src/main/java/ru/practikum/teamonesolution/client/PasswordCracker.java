package ru.practikum.teamonesolution.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PasswordCracker {
    public static void main(String[] args) throws IOException {
        String apiUrl = "http://ya.praktikum.fvds.ru:8080/dev-day/task/3";
        String authToken = "d63cf677-33fb-4e3a-991f-526165c2973d";
        String charset = "0123456789ABCDEFabcdef";

        // Начнем с середины диапазона
        String password = findPassword(apiUrl, authToken, charset, "", charset);

        System.out.println("Пароль найден: " + password);
    }

    private static String findPassword(String apiUrl, String authToken, String charset, String lowerBound, String upperBound) throws IOException {
        while (lowerBound.length() <= 8) {
            // Вычисляем середину текущего диапазона
            int middleIndex = (lowerBound.length() + upperBound.length()) / 2;
            String password = lowerBound + charset.charAt(charset.length() / 2) + upperBound.substring(middleIndex);

            // Формируем JSON-запрос с текущим паролем
            JsonObject jsonRequest = new JsonObject();
            jsonRequest.addProperty("password", password);

            // Отправляем запрос на сервер
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("AUTH_TOKEN", authToken);
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonRequest.toString().getBytes());
            }

            // Получаем код состояния HTTP
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                // Пароль найден
                return password;
            } else {
                // Пароль не подходит, учитываем подсказку сервера
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    String response = in.readLine();
                    JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
                    String prompt = jsonResponse.get("prompt").getAsString();
                    if ("<pass".equals(prompt)) {
                        // Пароль меньше требуемого
                        lowerBound = password;
                    } else if (">pass".equals(prompt)) {
                        // Пароль больше требуемого
                        upperBound = password;
                    } else {
                        // Неверная подсказка, ошибка
                        System.out.println("Ошибка: неверная подсказка от сервера.");
                        break;
                    }
                }
            }

            connection.disconnect();
        }

        return "";
    }
}
