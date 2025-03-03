package fr.afpa.covoiturafpa.model;

import javax.persistence.CascadeType;
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
@Table(name = "destination", schema = "heroku_ext")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destination")
    private Integer id;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city")
    private City city;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTravel() {
        if (this.isFromAfpa) {
            return "AFPA -> " + this.city.getName();
        }
        else {
            return this.city.getName() + " -> AFPA";
        }
    }
}
