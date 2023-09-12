package ru.practikum.teamonesolution;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practikum.teamonesolution.client.ProgrammerDayClient;
import ru.practikum.teamonesolution.models.BadResponse;
import ru.practikum.teamonesolution.models.Password;
import ru.practikum.teamonesolution.service.Decoder;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class TeamOneSolutionApplication {

    public static void main(String[] args) {
//        String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
//                "E", "F", "a", "b", "c", "d", "e", "f"};
//        int[] pass = new int[]{21, 21, 21, 21, 21, 21, 21, 21};
//        ProgrammerDayClient programmerDayClient = new ProgrammerDayClient();
//        System.out.println(generateCombinations(chars, programmerDayClient));

        ProgrammerDayClient programmerDayClient = new ProgrammerDayClient();

        System.out.println(programmerDayClient.register());

    }


    public static Password generateCombinations(String[] chars, ProgrammerDayClient programmerDayClient) {

        Password password = new Password();
        StringBuilder builder = new StringBuilder();
        int minSize = 8;
        int status = 0;
        boolean isLess = false;

        for (int i = 0; i <= 8; i++) {
            builder.append(0);
            String data = "{\"password\": \"" + builder + "\"}";
            password = programmerDayClient.getTask(data);
            if (isPromptGreaterThan(password.getJson())) {
                builder.delete(i, i + 1);
                break;
            }
        }

        for (int i = 0; i < builder.length(); i++) {
            boolean isFound = false;
            int left = 0;
            int right = 21;
            while (!isFound) {
                int middle = (left + right) / 2;
                builder.replace(i, i + 1, chars[middle]);
                String data = "{\"password\": \"" + builder + "\"}";
                password = programmerDayClient.getTask(data);
                if (password.getStatus() == 200) {
                    return password;
                }
                if (isPromptGreaterThan(password.getJson())) {
                    right = middle;
                } else {
                    left = middle;
                    builder.replace(i, i + 1, chars[middle + 1]);
                    String data1 = "{\"password\": \"" + builder + "\"}";
                    password = programmerDayClient.getTask(data1);
                    if (password.getStatus() == 200) {
                        return password;
                    }
                    if (isPromptGreaterThan(password.getJson())) {
                        isFound = true;
                    }
                }
            }
        }

        return password;
    }

    private static boolean isPromptGreaterThan(String json) {
        Gson gson = new Gson();
        BadResponse response = gson.fromJson(json, BadResponse.class);

        String prompt = response.getPromt();

        return ">pass".equals(prompt);
    }
}
