package fr.afpa.covoiturafpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "partner", schema = "covoiturafpa")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partner")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo_picture_path")
    private String logoPicturePath;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_centre")
    private Centre centre;
    

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
    
    public String getLogoPicturePath() {
        return logoPicturePath;
    }

    public void setLogoPicturePath(String logoPicturePath) {
        this.logoPicturePath = logoPicturePath;
    }

    public Partner() {
    }
}

