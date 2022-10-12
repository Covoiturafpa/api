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

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonTypeName("T")
@Entity
@Table(name = "trainee")
@DiscriminatorValue("T")
public class Trainee extends Person {

    @JsonView(Views.SimpleUser.class)
    @ManyToOne
    @JoinColumn(name = "id_formation")
    private Formation formation;

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Trainee() {
    }
}
