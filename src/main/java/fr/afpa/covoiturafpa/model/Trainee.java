package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "trainee")
public class Trainee extends User {

    @Column(name = "start_training")
    private LocalDate startTraining;

    @Column(name = "end_training")
    private LocalDate endTraining;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    private Formation formation;


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
