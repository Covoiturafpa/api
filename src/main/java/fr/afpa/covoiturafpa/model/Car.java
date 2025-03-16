package fr.afpa.covoiturafpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "car", schema = "covoiturafpa")
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
    @JsonBackReference
    private Person person;
        
    public Car() {
    }

    public int getId() {
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
}
