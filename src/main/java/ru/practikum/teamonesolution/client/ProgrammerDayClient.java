package ru.practikum.teamonesolution.client;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practikum.teamonesolution.models.Task;
import ru.practikum.teamonesolution.service.Decoder;
import ru.practikum.teamonesolution.service.TaskService;
import ru.practikum.teamonesolution.service.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class ProgrammerDayClient {
    private String apiToken;
    private final String url = "http://ya.praktikum.fvds.ru:8080/dev-day/task/2";
    private final HttpClient client= HttpClient.newHttpClient();
    private final TaskService taskService;
    private final Gson gson = Util.getGson();

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
            String decoded = Decoder.decode(body, )
            taskService.add(task);
            return body;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Execution problem was not resolved", e.getCause());
        }
    }
}
