package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@DiscriminatorColumn(name="person_type")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String surname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_activated")
    private boolean isActivated;

    @Column(name = "contact_by_sms")
    private boolean contactBySms;

    @Column(name = "contact_by_mail")
    private boolean contactByMail;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Car> cars;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private Set<RidePassenger> rides = new HashSet<RidePassenger>();

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

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<RidePassenger> getRides() {
        return rides;
    }

    public void setRides(Set<RidePassenger> rides) {
        this.rides = rides;
    }

    public User() {
    }
}
