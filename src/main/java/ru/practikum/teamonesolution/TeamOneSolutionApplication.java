package ru.practikum.teamonesolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practikum.teamonesolution.models.Participant;
import ru.practikum.teamonesolution.models.Team;

@SpringBootApplication
public class TeamOneSolutionApplication {

	public static void main(String[] args) {

		Participant participant = new Participant()

		Team team = new Team();


		SpringApplication.run(TeamOneSolutionApplication.class, args);

		/*{"name": "Слабоумие и отвага",
				"gitHubUrl":"https://github.com/bbzen/team-one-solution",
				"participants": [
			{"email": "a.lyagushin@yandex.ru", "cohort": "java_28", "firstName": "Aleksandr", "lastName":"Lyagushin"},
			{"email": "kalyghnii@yandex.ru", "cohort": "java_29", "firstName": "Aleksandr", "lastName":"Kalyghnii"},
			{"email": "dimonsterus007@yandex.ru", "cohort": "java_30", "firstName": "Dmitrii", "lastName":"Derendyaev"},
			{"email": "gagarin.sn@yandex.ru", "cohort": "java_23", "firstName": "Stas", "lastName":"Gagarin"}
]}*/
	}

}
