package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import fr.afpa.covoiturafpa.model.RidePassenger.Status;
import fr.afpa.covoiturafpa.model.utils.Views;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "rideType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RecurringRide.class, name = "R"),
        @JsonSubTypes.Type(value = OneTimeRide.class, name = "O")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ride_type")
@Table(name = "ride", schema = "covoiturafpa")
public class Ride {

    @JsonView(Views.SimpleRide.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ride")
    private Integer id;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonView(Views.SimpleRide.class)
    @Column(name = "departure_time")
    private LocalTime departureTime;

    @JsonView(Views.DetailedRide.class)
    @Column(name = "is_active")
    private boolean isActive;

    @JsonView(Views.SimpleRide.class)
    @Column
    private String comment;

    @JsonView(Views.SimpleRide.class)
    @Column
    private float price;

    @JsonView(Views.SimpleRide.class)
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "id_destination")
    private Destination destination;

    @JsonView(Views.SimpleRide.class)
    @ManyToOne
    @JoinColumn(name = "id_car")
    private Car car;

    @JsonView(Views.DetailedRide.class)
    @OneToMany(mappedBy = "ride", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<RidePassenger> requestedPassengers = new ArrayList<RidePassenger>();

    @JsonView(Views.SimpleRide.class)
    @Column(name = "ride_type", nullable = false, insertable = false, updatable = false)
    private String rideType;

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String type) {
        this.rideType = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public List<RidePassenger> getRequestedPassengers() {
        return requestedPassengers;
    }

    public void setRequestedPassengers(List<RidePassenger> passengers) {
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

    // TODO: methode cout trajet (distance?)
    public float calculateCost() {
        return 0;
    }

    public Person getDriver() {
        return this.car.getPerson();
    }

    public void setDriver(Person driver) {
        RidePassenger ridePassenger = new RidePassenger(driver, this, true, Status.ACCEPTED, LocalDateTime.now());
        driver.getRides().add(ridePassenger);
        this.requestedPassengers.removeIf((requestedPassenger) -> (requestedPassenger.getIsDriver()));
        this.requestedPassengers.add(ridePassenger);
    }

    public boolean addBooking(Person person) {
        RidePassenger ridePassenger = new RidePassenger(person, this, false, Status.PENDING, LocalDateTime.now());
        person.getRides().add(ridePassenger);
        return this.requestedPassengers.add(ridePassenger);
    }

    public Ride manageBooking(Person person, boolean isAccepted) {
        if (isAccepted) {
            this.acceptBooking(person);
        } else {
            this.rejectBooking(person);
        }
        return this;
    }

    public void acceptBooking(Person person) {
        if (this.hasBooking(person)) {
            this.requestedPassengers.get(this.findBookingIndex(person)).setStatus(Status.ACCEPTED);
        }
    }

    public boolean hasBooking(Person person) {
        int index = this.findBookingIndex(person);
        if (index != -1) {
            return (this.requestedPassengers.get(index).getId().getIdPerson() == person.getId());
        } else {
            return false;
        }
    }

    public RidePassenger rejectBooking(Person person) {
        return this.requestedPassengers.remove(this.findBookingIndex(person));
    }

    public int findBookingIndex(Person person) {
        for (int i = 0; i < this.requestedPassengers.size(); i++) {
            if (this.requestedPassengers.get(i).getId().getIdPerson() == person.getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + Float.floatToIntBits(price);
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((car == null) ? 0 : car.hashCode());
        result = prime * result + ((requestedPassengers == null) ? 0 : requestedPassengers.hashCode());
        result = prime * result + ((rideType == null) ? 0 : rideType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ride other = (Ride) obj;
        if (departureTime == null) {
            if (other.departureTime != null)
                return false;
        } else if (!departureTime.equals(other.departureTime))
            return false;
        if (isActive != other.isActive)
            return false;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (car == null) {
            if (other.car != null)
                return false;
        } else if (!car.equals(other.car))
            return false;
        if (requestedPassengers == null) {
            if (other.requestedPassengers != null)
                return false;
        } else if (!requestedPassengers.equals(other.requestedPassengers))
            return false;
        if (rideType == null) {
            if (other.rideType != null)
                return false;
        } else if (!rideType.equals(other.rideType))
            return false;
        return true;
    }

}