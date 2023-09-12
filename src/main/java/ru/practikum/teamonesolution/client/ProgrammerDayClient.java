package ru.practikum.teamonesolution.client;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import ru.practikum.teamonesolution.models.Answer;
import ru.practikum.teamonesolution.service.Decoder;
import ru.practikum.teamonesolution.service.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ProgrammerDayClient {
    private String apiToken;
    private final String url = "http://ya.praktikum.fvds.ru:8080/dev-day/task/4";
    private final HttpClient client = HttpClient.newHttpClient();
    // private final TaskService taskService;
    private final Gson gson = Util.getGson();
    private final String regex = "<code id=\"congratulation\"><span>(.+)</span></code></p>";

    public String register() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("AUTH_TOKEN", "d63cf677-33fb-4e3a-991f-526165c2973d")
                .GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Something went wrong");
            }
            String body = response.body();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(body);

            if (matcher.find()) {
                String encoded = matcher.group(1);
                int offset = Integer.parseInt(matcher.group(2));

                String decoded = Decoder.decode(encoded, offset);
                return decoded;
            } else {
                throw new RuntimeException("JSON not found in HTML.");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
    }

    public Answer sendAnswer(String data) {
        Answer answer = new Answer();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("AUTH_TOKEN", "d63cf677-33fb-4e3a-991f-526165c2973d")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Something went wrong");
            }
            String body = response.body();
            answer.setStatus(response.statusCode());
            answer.setJson(body);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
        return answer;
    }

    public Answer getTask() {
        Answer answer = new Answer();
        String encoded = null;
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("AUTH_TOKEN", "d63cf677-33fb-4e3a-991f-526165c2973d")
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(data)).build();
                .GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(response.body());

            if (matcher.find()) {
                encoded = matcher.group(1);
            } else {
                throw new RuntimeException("JSON not found in HTML.");
            }
            Charset koi8 = Charset.forName("KOI8-R");
            Charset iso = Charset.forName("ISO-8859-1");
            Charset jmb = Charset.forName("IBM866");
            Charset win = Charset.forName("WINDOWS-1251");

            List<Charset> charsets = List.of(
                    Charset.forName("KOI8-R"),
                    Charset.forName("ISO-8859-1"),
                    Charset.forName("IBM866"),
                    Charset.forName("WINDOWS-1251")
            );

            for (Charset charset : charsets) {
                for (int i = 0; i < 4; i++) {
                    String row = new String(encoded.getBytes(charset), charsets.get(i));
                    String regex = "Поздравляем";
                    String[] words = row.split(" ");
                    if (words[0].equals(regex)) {
                        answer.setJson(row);
                        return answer;
                    }
                }
            }

            answer.setJson(new String(encoded.getBytes(koi8), StandardCharsets.UTF_8));


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
        return answer;
    }

}
