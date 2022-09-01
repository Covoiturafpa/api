package fr.afpa.covoiturafpa.model;

import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Ride {
    private int id;
    private LocalTime departureTime;
    private boolean isActive;
    private String comment;
    private Destination destination;
    private Car car;
    private User driver;
    private ArrayList<User> passengers;
    private ArrayList<User> reservations; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public ArrayList<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<User> passengers) {
        this.passengers = passengers;
    }

    public ArrayList<User> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<User> reservations) {
        this.reservations = reservations;
    }

    public Ride() {
    }


    public int countFreeSeats() {
        return 0;
    }
}