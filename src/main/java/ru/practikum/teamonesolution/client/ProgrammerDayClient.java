package ru.practikum.teamonesolution.client;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practikum.teamonesolution.models.Task;
import ru.practikum.teamonesolution.models.Task1Model;
import ru.practikum.teamonesolution.service.Decoder;
import ru.practikum.teamonesolution.service.TaskService;
import ru.practikum.teamonesolution.service.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ProgrammerDayClient {
    private String apiToken;
    private final String url = "http://ya.praktikum.fvds.ru:8080/dev-day/task/2";
    private final HttpClient client= HttpClient.newHttpClient();
   // private final TaskService taskService;
    private final Gson gson = Util.getGson();
    String regex = "(<code id=\"message\"><span>{&quot;encoded&quot;: &quot;).+";

    public String register() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("AUTH_TOKEN", "d63cf677-33fb-4e3a-991f-526165c2973d")
                .GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Smth went wrong");
            }
            String body = response.body();
            Pattern rowData = Pattern.compile(body);
            List<String> parsed = rowData.matcher(regex)
                    .results()
                    .map(matcher -> matcher.group(0))
                    .collect(Collectors.toList());
            Task1Model task = gson.fromJson(parsed.get(0), Task1Model.class);
            String decoded = Decoder.decode(task.getEncoded(), task.getOffset());
            return decoded;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
    }

    public String getTask(String data) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("AUTH_TOKEN", "d63cf677-33fb-4e3a-991f-526165c2973d")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Smth went wrong");
            }
            String body = response.body();
            return body;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
    }

    public String getTask1(String data) {
        Pattern rowData = Pattern.compile(data);
        List<String> parsed = rowData.matcher(regex)
                .results()
                .map(matcher -> matcher.group(0))
                .collect(Collectors.toList());
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("AUTH_TOKEN", "d63cf677-33fb-4e3a-991f-526165c2973d")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Smth went wrong");
            }

            String body = response.body();
            return body;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
    }

}
