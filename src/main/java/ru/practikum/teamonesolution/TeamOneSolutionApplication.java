package ru.practikum.teamonesolution;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practikum.teamonesolution.client.ProgrammerDayClient;
import ru.practikum.teamonesolution.models.BadResponse;
import ru.practikum.teamonesolution.models.Password;
import ru.practikum.teamonesolution.service.Decoder;

@SpringBootApplication
public class TeamOneSolutionApplication {

    public static void main(String[] args) {
        char[] chars = new char[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f'};
        System.out.println(generateCombinations("", "0123456789ABCDEFabcdef", 8));

    }


    public static String generateCombinations(String prefix, String characters, int remainingLength) {
        ProgrammerDayClient programmerDayClient = new ProgrammerDayClient();
        Password password = new Password();
        int[] pass = new int[]{11, 11, 11, 11, 10, 10, 10, 10};
        StringBuilder builder = new StringBuilder();
        int status = 0;
        int sign = 7;

        while (status != 200) {

            for (int i : pass) {
                builder.append(pass[i]);
            }

            String dataToSend = "{\"password\": \"" + builder.toString() + "\"}";

            Password newPass = programmerDayClient.getTask(dataToSend);
            status = newPass.getStatus();


        }

    }

    private static boolean isPromptGreaterThan(String json) {
        Gson gson = new Gson();
        BadResponse response = gson.fromJson(json, BadResponse.class);

        String prompt = response.getPromt();

        return ">pass".equals(prompt);
    }
