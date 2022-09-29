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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = As.PROPERTY,
    property = "personType")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "E"),
        @JsonSubTypes.Type(value = Trainee.class, name = "T")
    })
@Entity
@DiscriminatorColumn(name="person_type")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public abstract class Person {

    @JsonView(Views.SimpleUser.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int id;

    @JsonView(Views.SimpleUser.class)
    @Column
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @JsonView(value = {Views.SimpleRide.class, Views.SimpleUser.class})
    @Column
    private String surname;

    @JsonView(value = {Views.SimpleRide.class, Views.SimpleUser.class})
    @Column(name = "first_name")
    private String firstName;

    @JsonView(value = {Views.SimpleUser.class, Views.DetailedRide.class})
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_activated")
    private boolean isActivated;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "contact_by_sms")
    private boolean contactBySms;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "contact_by_mail")
    private boolean contactByMail;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    private Set<Notification> notifications;

    @JsonView(Views.DetailedUser.class)
    @OneToMany(mappedBy = "person")
    private Set<Car> cars;

    @JsonBackReference
    @OneToMany(mappedBy = "person")
    private Set<RidePassenger> rides = new HashSet<RidePassenger>();

    @JsonView(Views.SimpleUser.class)
    @Column(name = "person_type")
    private String personType;


    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

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

    public boolean getIsActivated() {
        return isActivated;
    }
    public void setIsActivated(boolean isActivated) {
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

    public Person() {
    }

}
