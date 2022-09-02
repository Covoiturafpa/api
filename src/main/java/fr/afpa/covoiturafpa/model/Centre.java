package fr.afpa.covoiturafpa.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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

    @OneToMany(mappedBy = "centre")
    private ArrayList<DayTimetable> daysTimetable;

    @OneToOne(mappedBy = "centre")
    private NotifConfig notifConfig;

    @OneToMany(mappedBy = "centre")
    private ArrayList<Partner> partners;

    @OneToMany(mappedBy = "centre")
    private ArrayList<Formation> formations;


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

    public ArrayList<DayTimetable> getDaysTimetable() {
        return daysTimetable;
    }

    public void setDaysTimetable(ArrayList<DayTimetable> daysTimetable) {
        this.daysTimetable = daysTimetable;
    }

    public NotifConfig getNotifConfig() {
        return notifConfig;
    }

    public void setNotifConfig(NotifConfig notifConfig) {
        this.notifConfig = notifConfig;
    }

    public ArrayList<Partner> getPartners() {
        return partners;
    }

    public void setPartners(ArrayList<Partner> partners) {
        this.partners = partners;
    }

    public ArrayList<Formation> getFormations() {
        return formations;
    }

    public void setFormations(ArrayList<Formation> formations) {
        this.formations = formations;
    }
}
