package ru.practikum.teamonesolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practikum.teamonesolution.models.Participant;
import ru.practikum.teamonesolution.models.Team;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TeamOneSolutionApplication {

	public static void main(String[] args) {

		Participant Lyagushin = new Participant("a.lyagushin@yandex.ru", "java_28", "Aleksandr", "Lyagushin");
		Participant Kalyghnii = new Participant("kalyghnii@yandex.ru", "java_29", "Aleksandr", "Kalyghnii");
		Participant Derendyaev = new Participant("dimonsterus007@yandex.ru", "java_30", "Dmitrii", "Derendyaev");
		Participant Gagarin = new Participant("gagarin.sn@yandex.ru", "java_23", "Stas", "Gagarin");

		ArrayList<Participant> participants = new ArrayList<>(List.of(Lyagushin, Kalyghnii, Derendyaev, Gagarin));
		Team team = new Team("Слабоумие и отвага", "https://github.com/bbzen/team-one-solution", participants);


		SpringApplication.run(TeamOneSolutionApplication.class, args);

	}

}
