package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

public class Trainee extends User {
    private int id;
    private LocalDate startTraining;
    private LocalDate endTraining;
    private Formation formation;
}
