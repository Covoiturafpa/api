package fr.afpa.covoiturafpa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
