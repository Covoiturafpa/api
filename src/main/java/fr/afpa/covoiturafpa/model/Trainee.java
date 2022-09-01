package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

public class Trainee extends User {
    private int idTrainee;
    private LocalDate startTraining;
    private LocalDate endTraining;
    private Formation formation;


    public int getIdTrainee() {
        return idTrainee;
    }

    public void setIdTrainee(int idTrainee) {
        this.idTrainee = idTrainee;
    }

    public LocalDate getStartTraining() {
        return startTraining;
    }

    public void setStartTraining(LocalDate startTraining) {
        this.startTraining = startTraining;
    }

    public LocalDate getEndTraining() {
        return endTraining;
    }

    public void setEndTraining(LocalDate endTraining) {
        this.endTraining = endTraining;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Trainee() {
    }
}
