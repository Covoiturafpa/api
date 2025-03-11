package fr.afpa.covoiturafpa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "centre", schema = "covoiturafpa")

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

    @Column(name = "contact_by_sms")
    private boolean contactBySms;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "id_centre")
    private List<DayTimetable> daysTimetable;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "id_centre")
    private List<Partner> partners;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "id_centre")
    private List<Formation> formations;


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

    public List<DayTimetable> getDaysTimetable() {
        return daysTimetable;
    }

    public void setDaysTimetable(List<DayTimetable> daysTimetable) {
        this.daysTimetable = daysTimetable;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public Centre() {
    }
}
