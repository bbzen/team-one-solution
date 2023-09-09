package ru.practikum.teamonesolution.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import ru.practikum.teamonesolution.client.ProgrammerDayClient;
import ru.practikum.teamonesolution.models.Task;
import ru.practikum.teamonesolution.models.Team;
import ru.practikum.teamonesolution.service.TeamService;


@Slf4j
@RestController
@RequestMapping("/teams")
@AllArgsConstructor
public class TeamController {
    private TeamService teamService;
    private ProgrammerDayClient programmerDayClient;

    @PostMapping("/register")
    public Task register(@RequestBody Team team) {
        return new Task().setTeamName("WTF").setApiToken("sldkfj").setNextTaskUrl("sjkdf");
    }

    @GetMapping("/task")
    public String add(@RequestBody String data) {
        return programmerDayClient.register(data, "/teams/register");
    }
}
