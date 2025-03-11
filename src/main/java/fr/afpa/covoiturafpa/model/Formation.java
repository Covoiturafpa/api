package fr.afpa.covoiturafpa.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@Entity
@Table(name = "formation", schema = "covoiturafpa")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formation")
    private int id;
    
    @JsonView(Views.SimpleUser.class)
    @Column
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_centre")
    private Centre centre;

    @JsonIgnore
    @ManyToMany(mappedBy = "taughtFormations")
    public List<Employee> teachers;

    public List<Employee> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Employee> teachers) {
        this.teachers = teachers;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Formation() {
    }
}
