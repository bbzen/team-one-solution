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

@SpringBootApplication
public class TeamOneSolutionApplication {

    public static void main(String[] args) {
        char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f'};
        int[] pass = new int[]{21, 21, 21, 21, 21, 21, 21, 21};
        String[] first = new String[2147483647];

        System.out.println(generateCombinations(pass, chars));

    }



    public static Password generateCombinations(char[] chars) {
        ProgrammerDayClient programmerDayClient = new ProgrammerDayClient();
        Password password = new Password();

        int status = 0;
        int sign = 7;
        boolean signChanged = false;
        int[] pass = new int[8];
        for (int i = 0; i < 7; i++) {
            pass[i] = 22;
        }

        while (status != 200) {

            for (int i = 0; i < 7; i++) {
                Map<Boolean, Integer> cross = Map.of(true, 0, false, 21);
                int left = 0;
                int right = 21;


                while (cross.get(true) - 1 == cross.get(false)) {
                    int middle = left + (right - left) / 2;
                    pass[i] = middle;
                    String newPass = null;
                    for (int k = 0; k < 7; k++) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(chars[k]);
                        newPass = builder.toString();
                    }
                    String dataToSend = "{\"password\": \"" + newPass + "\"}";
                    password = programmerDayClient.getTask(dataToSend);
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
