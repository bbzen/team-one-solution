package ru.practikum.teamonesolution.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Task1Model {
    @NotNull
    private String encoded;

    @NotNull
    private int offset;


}
