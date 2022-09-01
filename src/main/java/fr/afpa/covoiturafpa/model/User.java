package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class User {
    private int id;
    private String email;
    private String password;
    private String surname;
    private String firstName;
    private String phoneNumber;
    private boolean isActivated;
    private boolean contactBySms;
    private boolean contactByMail;
    private LocalDateTime lastLogin;
    private ArrayList<Notification> notifications;
    private ArrayList<Car> cars;
    private ArrayList<Ride> currentRides;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActivated() {
        return isActivated;
    }
    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public boolean isContactBySms() {
        return contactBySms;
    }

    public void setContactBySms(boolean contactBySms) {
        this.contactBySms = contactBySms;
    }

    public boolean isContactByMail() {
        return contactByMail;
    }

    public void setContactByMail(boolean contactByMail) {
        this.contactByMail = contactByMail;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public ArrayList<Ride> getCurrentRides() {
        return currentRides;
    }

    public void setCurrentRides(ArrayList<Ride> currentRides) {
        this.currentRides = currentRides;
    }

    public User() {
    }
}
