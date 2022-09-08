package fr.afpa.covoiturafpa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RidePassengerId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Column(name="id_person")
    private Integer idUser;

    @Column(name="id_ride")
    private Integer idRide;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRide() {
        return idRide;
    }
    
    public void setIdRide(int idRide) {
        this.idRide = idRide;
    }

    public RidePassengerId() {
    }

    public RidePassengerId(int idUser, int idRide) {
        this.idUser = idUser;
        this.idRide = idRide;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idRide == null) ? 0 : idRide.hashCode());
        result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
        RidePassengerId other = (RidePassengerId) obj;
        return Objects.equals(getIdRide(), other.getIdRide()) && Objects.equals(getIdUser(), other.getIdUser());
    }
}
