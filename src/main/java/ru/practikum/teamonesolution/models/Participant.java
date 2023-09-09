package ru.practikum.teamonesolution.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Participant {
    @NotNull
    private String email;

    @NotNull
    private String cohort;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
}
