package fr.afpa.covoiturafpa.model;

import java.util.ArrayList;

public class Centre {
    private int id;
    private String name;
    private String address;
    private float latitude;
    private float longitude;
    private String phoneNumber;
    private ArrayList<DayTimetable> daysTimetable;
    private NotifConfig notifConfig;
    private ArrayList<Partner> partners;
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
