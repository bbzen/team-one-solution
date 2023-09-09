package ru.practikum.teamonesolution.client;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import ru.practikum.teamonesolution.models.Task;
import ru.practikum.teamonesolution.service.TaskService;
import ru.practikum.teamonesolution.service.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ProgrammerDayClient {
    private String apiToken;
    private final String url = "http://localhost:8080";
    private final HttpClient client = HttpClient.newHttpClient();
    private final TaskService taskService = new TaskService();
    private final Gson gson = Util.getGson();

    public String register(String data, String endPoint) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url + endPoint)).POST(
                HttpRequest.BodyPublishers.ofString(data)).header("Content-Type", "application/json").build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Smth went wrong");
            }
            String body = response.body();
            Task task = gson.fromJson(body, Task.class);
            taskService.add(task);
            apiToken = task.getApiToken();
            return body;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
    }
}
