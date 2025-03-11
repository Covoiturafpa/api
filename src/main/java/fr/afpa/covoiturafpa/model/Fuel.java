package fr.afpa.covoiturafpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@Entity
@Table(name = "fuel", schema = "covoiturafpa")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fuel")
    private int id;

    @JsonView(Views.DetailedUser.class)
    @Column
    private String name;

    @Column(name = "price_by_unit")
    private float priceByUnit;


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

    public float getPriceByUnit() {
        return priceByUnit;
    }

    public void setPriceByUnit(float priceByUnit) {
        this.priceByUnit = priceByUnit;
    }

    public Fuel() {
    }
}
