package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.utils.Views;

@JsonTypeName("T")
@Entity
@Table(name = "trainee")
@DiscriminatorValue("T")
public class Trainee extends Person {

    @JsonView(Views.DetailedUser.class)
    @Column(name = "start_training")
    private LocalDate startTraining;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "end_training")
    private LocalDate endTraining;

    @JsonView(Views.SimpleUser.class)
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
