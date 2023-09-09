package ru.practikum.teamonesolution.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
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
