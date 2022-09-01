package fr.afpa.covoiturafpa.model;

public class Destination {
    private int id;
    private double latitude;
    private double longitude;
    private boolean isFromAfpa;
    private City city;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isFromAfpa() {
        return isFromAfpa;
    }

    public void setFromAfpa(boolean isFromAfpa) {
        this.isFromAfpa = isFromAfpa;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Destination() {
    }
}
