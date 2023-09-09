package ru.practikum.teamonesolution.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Task {
    private int id;
    private String teamName;
    private String apiToken;
    private String nextTaskUrl;
}
