package ru.practikum.teamonesolution;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practikum.teamonesolution.client.ProgrammerDayClient;
import ru.practikum.teamonesolution.models.BadResponse;
import ru.practikum.teamonesolution.models.Answer;


@SpringBootApplication
public class TeamOneSolutionApplication {

    public static void main(String[] args) {
        String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
                "E", "F", "a", "b", "c", "d", "e", "f"};
        ProgrammerDayClient programmerDayClient = new ProgrammerDayClient();
        String dataToSend = "{\"congratulation\": \"" + programmerDayClient.getTask().getJson() + "\"}";
        System.out.println(dataToSend);
        System.out.println(programmerDayClient.sendAnswer(dataToSend));
    }


    public static Answer generateCombinations(String[] chars, ProgrammerDayClient programmerDayClient) {

        Answer answer = new Answer();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i <= 8; i++) {
            builder.append(0);
            String data = "{\"answer\": \"" + builder + "\"}";
//            answer = programmerDayClient.getTask(data);
            if (isPromptGreaterThan(answer.getJson())) {
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
                String data = "{\"answer\": \"" + builder + "\"}";
//                answer = programmerDayClient.getTask(data);
                if (answer.getStatus() == 200) {
                    return answer;
                }
                if (isPromptGreaterThan(answer.getJson())) {
                    right = middle;
                } else {
                    left = middle;
                    builder.replace(i, i + 1, chars[middle + 1]);
                    String data1 = "{\"answer\": \"" + builder + "\"}";
//                    answer = programmerDayClient.getTask(data1);
                    if (answer.getStatus() == 200) {
                        return answer;
                    }
                    if (isPromptGreaterThan(answer.getJson())) {
                        isFound = true;
                    }
                }
            }
        }

        return answer;
    }

    private static boolean isPromptGreaterThan(String json) {
        Gson gson = new Gson();
        BadResponse response = gson.fromJson(json, BadResponse.class);

        String prompt = response.getPromt();

        return ">pass".equals(prompt);
    }
}
