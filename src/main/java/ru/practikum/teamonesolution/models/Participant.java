package ru.practikum.teamonesolution.models;

public class Participant {
    private String email;
    private String cohort;
    private String firstName;
    private String lastName;

    public Participant(String email, String cohort, String firstName, String lastName) {
        this.email = email;
        this.cohort = cohort;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Participants{" +
                "email='" + email + '\'' +
                ", cohort='" + cohort + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
