package ru.practikum.teamonesolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practikum.teamonesolution.client.ProgrammerDayClient;
import ru.practikum.teamonesolution.models.Password;
import ru.practikum.teamonesolution.service.Decoder;

@SpringBootApplication
public class TeamOneSolutionApplication {

	public static void main(String[] args) {


		System.out.println(generateCombinations("", "0123456789ABCDEFabcdef", 8));

	}

	public static String generateCombinations(String prefix, String characters, int remainingLength) {
		ProgrammerDayClient programmerDayClient = new ProgrammerDayClient();
		Password password = new Password();
		if (remainingLength == 0) {
			System.out.println(prefix);
			return prefix;
		}

		for (int i = 0; i < characters.length(); i++) {
			String newPrefix = prefix + characters.charAt(i);
			String dataToSend = "{\"password\": \"" + newPrefix + "\"}";
			programmerDayClient.getTask(dataToSend);
			if (password.getStatus() == 200) {
				return password.getJson();
			};

			generateCombinations(newPrefix, characters, remainingLength - 1);
		}
		return prefix;
	}

}
