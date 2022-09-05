//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
//https://thorben-janssen.com/hibernate-tip-many-to-many-association-with-additional-attributes/


package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "ride_passenger")
public class RidePassenger {

    @EmbeddedId
    private RidePassengerId id = new RidePassengerId();

    @ManyToOne
    @MapsId("idUser")
    private User user;

    @ManyToOne
    @MapsId("idRide")
    private Ride ride;

    @Column(name = "status_type")
    private Status status_type;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    enum Status {
        WAITING,
        DOING,
        FINISHED
    }


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

    public Status getStatus_type() {
        return status_type;
    }

    public void setStatus_type(Status status_type) {
        this.status_type = status_type;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public RidePassenger() {
    }  
}
