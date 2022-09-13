package fr.afpa.covoiturafpa.model;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="ride_type")
@Table(name = "ride")
public abstract class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ride")
    private int id;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "is_active")
    private boolean isActive;

    @Column
    private String comment;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_destination")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "id_car")
    private Car car;

    @JsonBackReference
    @OneToMany(mappedBy = "ride")
    private Set<RidePassenger> requestedPassengers = new HashSet<RidePassenger>();

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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public Set<RidePassenger> getPossiblePassengers() {
        return requestedPassengers;
    }

    public void setPossiblePassengers(Set<RidePassenger> passengers) {
        this.requestedPassengers = passengers;
    }

    public Ride() {
    }

    public int countFreeSeats() {
        return 0;
    }
}