package ru.practikum.teamonesolution.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Task1Response {
    @NotNull
    private String decoded;
}
