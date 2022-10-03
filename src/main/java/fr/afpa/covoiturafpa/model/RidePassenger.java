//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
//https://thorben-janssen.com/hibernate-tip-many-to-many-association-with-additional-attributes/


package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@Entity
@Table(name = "ride_passenger")
public class RidePassenger {

    @JsonIgnore
    @EmbeddedId
    private RidePassengerId id = new RidePassengerId();

    @JsonView(Views.DetailedRide.class)
    @ManyToOne
    @MapsId("idPerson")
    @JoinColumn(name="id_person")
    private Person person;

    @ManyToOne
    @MapsId("idRide")
    @JoinColumn(name="id_ride")
    private Ride ride;

    @JsonView(Views.DetailedRide.class)
    @Column(name = "is_driver")
    private Boolean isDriver;

    @JsonView(Views.DetailedRide.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    enum Status {
        PENDING,
        ACCEPTED,
        FINISHED
    }


    public RidePassengerId getId() {
        return id;
    }

    public void setId(RidePassengerId id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }

    public RidePassenger() {
    }
}
