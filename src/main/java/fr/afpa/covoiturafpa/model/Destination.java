package fr.afpa.covoiturafpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destination")
    private int id;

    @JsonView(Views.SimpleRide.class)
    @Column
    private double latitude;

    @JsonView(Views.SimpleRide.class)
    @Column
    private double longitude;

    @JsonView(Views.SimpleRide.class)
    @Column(name = "is_from_afpa")
    private boolean isFromAfpa;

    @JsonView(Views.SimpleRide.class)
    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean getIsFromAfpa() {
        return isFromAfpa;
    }

    public void setIsFromAfpa(boolean isFromAfpa) {
        this.isFromAfpa = isFromAfpa;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Destination() {
    }
}
