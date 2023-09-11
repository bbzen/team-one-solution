package ru.practikum.teamonesolution.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BadResponse {
    @NotNull
    private String completed;

    @NotNull
    private String promt;
}