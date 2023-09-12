package ru.practikum.teamonesolution.client;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerRequester {
    private final String url = "http://ya.praktikum.fvds.ru:8080/dev-day/task/4";
    private final String authToken = "d63cf677-33fb-4e3a-991f-526165c2973d";
    private final String regex = "<code id=\"congratulation\"><span>(.+)</span></code></p>";

    public void sendRequests() {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            while (true) {
                // Отправляем GET запрос с заголовком "AUTH_TOKEN"
                HttpRequest getRequest = HttpRequest.newBuilder(URI.create(url))
                        .header("AUTH_TOKEN", authToken)
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                String htmlContent = response.body();

                // Поиск закодированного значения с помощью регулярного выражения
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(htmlContent);

                if (matcher.find()) {
                    String encodedValue = matcher.group(1);

                    // Декодируем значение (ваш код для определения кодировки)
                    String decodedValue = decodeValue(encodedValue);

                    // Отправляем POST запрос с раскодированным значением
                    int postResponseCode = sendPostRequest(decodedValue);

                    // Проверяем, что POST запрос вернул код 200
                    if (postResponseCode == 200) {
                        break; // Выход из цикла, если код 200
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String decodeValue(String encodedValue) {
        // Массив наиболее распространенных кодировок для проверки
        Charset[] charsetsToCheck = {StandardCharsets.UTF_8, StandardCharsets.ISO_8859_1, Charset.forName("Windows-1251")}; // и другие кодировки по необходимости

        for (Charset charset : charsetsToCheck) {
            try {
                byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
                String decodedString = new String(decodedBytes, charset);

                // Проверяем, является ли результат декодирования UTF-8
                if (StandardCharsets.UTF_8.equals(charset)) {
                    return decodedString;
                }
            } catch (IllegalArgumentException e) {
                // Произошла ошибка при декодировании, продолжаем проверку с другой кодировкой
                continue;
            }
        }

        // Если не удалось найти кодировку UTF-8, возвращаем null или генерируем исключение
        return null; // Или генерируйте исключение, если нужно обработать ошибку
    }

    private int sendPostRequest(String decodedValue) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            String postUrl = "http://ya.praktikum.fvds.ru:8080/dev-day/task/4"; // URL для POST запроса

            // Создаем JSON объект с раскодированным значением
            JSONObject json = new JSONObject();
            json.put("congratulation", decodedValue);

            // Отправляем POST запрос с заголовком "AUTH_TOKEN"
            HttpRequest postRequest = HttpRequest.newBuilder(URI.create(postUrl))
                    .header("Content-Type", "application/json")
                    .header("AUTH_TOKEN", authToken)
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("POST Response Code: " + response.statusCode());
            System.out.println("POST Response Body: " + response.body());

            return response.statusCode(); // Возвращаем код ответа POST запроса

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1; // В случае ошибки возвращаем -1 или другой код, который указывает на ошибку
        }
    }

    public static void main(String[] args) {
        ServerRequester requester = new ServerRequester();
        requester.sendRequests();
    }
}
