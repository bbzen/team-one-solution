package ru.practikum.teamonesolution.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Participant {
    private String email;
    private String cohort;
    private String firstName;
    private String lastName;
}
