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

@Entity
@Table(name = "ride_passenger")
public class RidePassenger {

    enum Status {
        PENDING,
        ACCEPTED,
        FINISHED
    }

    @EmbeddedId
    @JsonIgnore
    private RidePassengerId id = new RidePassengerId();

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name="id_person")
    private User user;

    @ManyToOne
    @MapsId("idRide")
    @JoinColumn(name="id_ride")
    private Ride ride;

    @Column(name = "is_driver")
    private Boolean isDriver;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status statusType;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;



    public RidePassengerId getId() {
        return id;
    }

    public void setId(RidePassengerId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Status getStatusType() {
        return statusType;
    }

    public void setStatusType(Status statusType) {
        this.statusType = statusType;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public RidePassenger() {
    }

    public Boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(Boolean isDriver) {
        this.isDriver = isDriver;
    }
}
