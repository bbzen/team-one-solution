package ru.practikum.teamonesolution.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practikum.teamonesolution.Storage.ParticipantStorage;
import ru.practikum.teamonesolution.models.Participant;
import ru.practikum.teamonesolution.models.Team;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private ParticipantStorage participantStorage;
    private final Team team = new Team();


    public Participant add(Participant participant) {
        return participantStorage.add(participant);
    }

    public List<Participant> getAll() {
        return participantStorage.getAll();
    }

    public Team createTeam(Team team) {
        this.team.setName(team.getName());
        this.team.setGitHubUrl(team.getGitHubUrl());
        this.team.setParticipants(team.getParticipants());
        return team;
    }

}
