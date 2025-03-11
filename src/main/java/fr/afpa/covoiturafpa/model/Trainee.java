package fr.afpa.covoiturafpa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonTypeName("T")
@Entity
@Table(name = "trainee", schema = "covoiturafpa")
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

    /**
     * Réimplémentation de "getAuthorities" afin d'ajouter le role "TRAINEE"
     * 
     * Euh... wait. Pas de rôle "TRAINEE" ? TODO or NOT TODO ?
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO OR MAYBE NOT ?
        return super.getAuthorities();
    }
}
