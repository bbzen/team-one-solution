package ru.practikum.teamonesolution.service;

import ru.practikum.teamonesolution.Storage.ParticipantStorage;
import ru.practikum.teamonesolution.models.Participant;
import ru.practikum.teamonesolution.models.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamService {
    private ParticipantStorage participantStorage;

    Participant Lyagushin = new Participant("a.lyagushin@yandex.ru", "java_28", "Aleksandr", "Lyagushin");
    Participant Kalyghnii = new Participant("kalyghnii@yandex.ru", "java_29", "Aleksandr", "Kalyghnii");
    Participant Derendyaev = new Participant("dimonsterus007@yandex.ru", "java_30", "Dmitrii", "Derendyaev");
    Participant Gagarin = new Participant("gagarin.sn@yandex.ru", "java_23", "Stas", "Gagarin");


    public Participant add(Participant participant) {
        return participantStorage.add(participant);
    }

    public List<Participant> getAll() {
        return participantStorage.getAll();
    }

    public Team createTeam() {
        participantStorage.add(Lyagushin);
        participantStorage.add(Kalyghnii);
        participantStorage.add(Gagarin);
        participantStorage.add(Derendyaev);
        return new Team(
                "Слабоумие и отвага",
                "https://github.com/bbzen/team-one-solution",
                participantStorage.getAll());
    }
}
