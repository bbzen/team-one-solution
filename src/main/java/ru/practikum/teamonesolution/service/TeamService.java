package ru.practikum.teamonesolution.service;

import ru.practikum.teamonesolution.Storage.ParticipantStorage;
import ru.practikum.teamonesolution.models.Participant;
import ru.practikum.teamonesolution.models.Team;

import java.util.List;

public class TeamService {
    private ParticipantStorage participantStorage;
    private Team team;

    public Participant add(Participant participant) {
        return participantStorage.add(participant);
    }

    public List<Participant> getAll() {
        return participantStorage.getAll();
    }

    public Team createTeam(Team team) {
        this.team = team;
        return team;
    }
}
