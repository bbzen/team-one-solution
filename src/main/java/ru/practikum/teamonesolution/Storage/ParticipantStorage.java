package ru.practikum.teamonesolution.Storage;

import ru.practikum.teamonesolution.models.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantStorage {
    private final List<Participant> participants = new ArrayList<>();

    public Participant add(Participant participant) {
        participants.add(participant);
        return participant;
    }

    public List<Participant> getAll() {
        return List.copyOf(participants);
    }
}
