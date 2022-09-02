package fr.afpa.covoiturafpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "partner")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partner")
    private int id;

    @Column(name = "logo_picture_path")
    private String logoPicturePath;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

