package ru.practikum.teamonesolution.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.practikum.teamonesolution.models.Team;
import ru.practikum.teamonesolution.service.TeamService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private TeamService teamService;

    @PostMapping("/register")
    public Team register() {
        return teamService.createTeam();
    }
}
