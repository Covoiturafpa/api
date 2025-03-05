package fr.afpa.covoiturafpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@Entity
@Table(name = "car_type",schema="covoiturafpa")
public class CarType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car_type")
    private int id;

    @JsonView(Views.DetailedUser.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CarTypeName name;

    @Column(name = "avg_fuel_consumption")
    private float avgFuelConsumption;

    @JsonView(Views.DetailedUser.class)
    @ManyToOne
    @JoinColumn(name = "id_fuel")
    private Fuel fuel;
    

    enum CarTypeName {
        COMPACT,
        BERLINE,
        SUV,
        MONOSPACE,
        UTILITAIRE
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarTypeName getName() {
        return name;
    }

    public void setCarTypeName(CarTypeName carTypeName) {
        this.name = carTypeName;
    }

    public float getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(float avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public CarType() {
    }
}