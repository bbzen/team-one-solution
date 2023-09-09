package ru.practikum.teamonesolution.models;

import lombok.Data;

@Data
public class Task {
    private int id;
    private String teamName;
    private String apiToken;
    private String nextTaskUrl;
}
