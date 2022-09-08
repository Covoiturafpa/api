package fr.afpa.covoiturafpa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "centre")
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centre")
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private float latitude;

    @Column
    private float longitude;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "id_notif_config", referencedColumnName = "id_notif_config")
    @JsonManagedReference
    private NotifConfig notifConfig;

    @OneToMany
    @JoinColumn(name = "id_centre")
    @JsonManagedReference
    private Set<DayTimetable> daysTimetable;

    @OneToMany
    @JoinColumn(name = "id_centre")
    @JsonManagedReference
    private Set<Partner> partners;

    @OneToMany
    @JoinColumn(name = "id_centre")
    @JsonManagedReference
    private Set<Formation> formations;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }
    
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<DayTimetable> getDaysTimetable() {
        return daysTimetable;
    }

    public void setDaysTimetable(Set<DayTimetable> daysTimetable) {
        this.daysTimetable = daysTimetable;
    }

    public NotifConfig getNotifConfig() {
        return notifConfig;
    }

    public void setNotifConfig(NotifConfig notifConfig) {
        this.notifConfig = notifConfig;
    }

    public Set<Partner> getPartners() {
        return partners;
    }

    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    public Set<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
    }

    public Centre() {

    }
}
