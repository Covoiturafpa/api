package fr.afpa.covoiturafpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.afpa.covoiturafpa.utils.Views;


@Entity
@Table(name = "car")
public class Car {

    @JsonView(Views.DetailedUser.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private int id;

    @JsonView(value = {Views.SimpleRide.class, Views.DetailedUser.class})
    @Column
    private String model;

    @JsonView(Views.DetailedUser.class)
    @Column
    private int seats;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "avg_fuel_consumption")
    private float avgFuelConsumption;

    @JsonView(Views.DetailedUser.class)
    @ManyToOne
    @JoinColumn(name = "id_car_type")
    private CarType carType;

    @JsonView(Views.SimpleRide.class)
    @ManyToOne
    @JoinColumn(name= "id_person")
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public float getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(float avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public Car() {
    }
}
