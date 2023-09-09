package ru.practikum.teamonesolution.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Team {
    private String name;
    private String gitHubUrl;
    private List<Participant> memberList;
}
