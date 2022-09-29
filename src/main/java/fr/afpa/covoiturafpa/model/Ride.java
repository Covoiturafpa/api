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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import fr.afpa.covoiturafpa.model.utils.Views;

import com.fasterxml.jackson.annotation.JsonView;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = As.PROPERTY,
    property = "rideType")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = RecurringRide.class, name = "R"),
        @JsonSubTypes.Type(value = OneTimeRide.class, name = "O")
    })
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="ride_type")
@Table(name = "ride")
public  class Ride {

    @JsonView(Views.SimpleRide.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ride")
    private int id;

    @JsonView(Views.SimpleRide.class)
    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "is_active")
    private boolean isActive;

    @JsonView(Views.SimpleRide.class)
    @Column
    private String comment;

    @JsonView(Views.SimpleRide.class)
    @Column
    private int price;

    @JsonView(Views.SimpleRide.class)
    @ManyToOne
    @JoinColumn(name = "id_destination")
    private Destination destination;

    @JsonView(Views.SimpleRide.class)
    @ManyToOne
    @JoinColumn(name = "id_car")
    private Car car;

    @JsonView(Views.DetailedRide.class)
    @OneToMany(mappedBy = "ride")
    private Set<RidePassenger> requestedPassengers = new HashSet<RidePassenger>();

    @JsonView(Views.SimpleRide.class)
    @Column(name = "ride_type")
    private String rideType;


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

    public Set<RidePassenger> getRequestedPassengers() {
        return requestedPassengers;
    }

    public void setRequestedPassengers(Set<RidePassenger> passengers) {
        this.requestedPassengers = passengers;
    }

    public Ride() {
    }

    public Ride(Destination destination) {
        this.destination = destination;
    }

    @JsonView(Views.SimpleRide.class)
    @JsonProperty("freeSeats")
    public int countFreeSeats() {
        return this.car.getSeats() - this.requestedPassengers.size();
    }

    //TODO: methode cout trajet (distance?)
    public float calculateCost() {
        return 0;
    }
}