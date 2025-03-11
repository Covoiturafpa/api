package fr.afpa.covoiturafpa.model;


import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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