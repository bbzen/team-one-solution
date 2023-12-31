package ru.practikum.teamonesolution.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Team {
    @NotNull
    private String name;

    @NotNull
    private String gitHubUrl;

    @NotNull
    private List<Participant> participants;
}
