package ru.practikum.teamonesolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practikum.teamonesolution.client.ProgrammerDayClient;
import ru.practikum.teamonesolution.service.Decoder;

@SpringBootApplication
public class TeamOneSolutionApplication {

	public static void main(String[] args) {

		ProgrammerDayClient programmerDayClient = new ProgrammerDayClient();

		String income = programmerDayClient.register();
		String data = "{\"decoded\": \"" + income + "\"}";
		System.out.println(data);
		String task = programmerDayClient.getTask(data);
		System.out.println(task);
	}

}
