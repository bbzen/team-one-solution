package ru.practikum.teamonesolution.client;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
//        encodedValue = encodedValue.replaceAll("[^A-Za-z0-9+/=]", "");

//        byte[] encodedBytes = Base64.getDecoder().decode(encodedValue);
//        byte[] encodedBytes = encodedValue.getBytes();

        // Массив наиболее распространенных кодировок для проверки
        Charset[] charsetsToCheck = {
                StandardCharsets.UTF_8,
                StandardCharsets.ISO_8859_1,
                Charset.forName("Windows-1251"),
                Charset.forName("UTF-16"),
                Charset.forName("ISO-8859-5"),
                Charset.forName("US-ASCII"),
                Charset.forName("UTF-16LE"),
                Charset.forName("UTF-16BE"),
                Charset.forName("UTF-32"),
                Charset.forName("UTF-32LE"),
                Charset.forName("UTF-32BE"),
                Charset.forName("KOI8-R"),
                // Добавьте другие кодировки по необходимости
        };

        for (Charset charset : charsetsToCheck) {
            byte[] byteArray = encodedValue.getBytes(charset);
            try {
                String decodedString = new String(byteArray, charset);
                for(Charset charset1: charsetsToCheck){
                    String newString = new String(byteArray, charset1);
                    System.out.println(newString);
                    if(newString.startsWith("Поздравляем")) {
                        return newString;
                    }
                }

                // Проверяем, является ли декодированная строка валидной JSON-строкой
                JSONObject jsonObject = new JSONObject(decodedString);

                // Если JSON-парсинг прошел успешно, возвращаем декодированное значение
                return decodedString;
            } catch (org.json.JSONException ignored) {
                // Произошла ошибка при декодировании или парсинге JSON, продолжаем проверку с другой кодировкой
            }
        }

        // Если не удалось найти подходящую кодировку, возвращаем null или генерируем исключение
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
