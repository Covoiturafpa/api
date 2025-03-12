package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "personType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "E"),
        @JsonSubTypes.Type(value = Trainee.class, name = "T")
})
@Entity
@DiscriminatorColumn(name = "person_type")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person", schema = "covoiturafpa")
public abstract class Person implements UserDetails {

    @JsonView(value = { Views.SimpleRide.class, Views.SimpleUser.class })
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer id;

    @JsonView(Views.SimpleUser.class)
    @Column
    private String email;

    @Column
    private String password;

    @JsonView(value = { Views.SimpleRide.class, Views.SimpleUser.class })
    @Column
    private String surname;

    @JsonView(value = { Views.SimpleRide.class, Views.SimpleUser.class })
    @Column(name = "first_name")
    private String firstName;

    @JsonView(value = { Views.SimpleUser.class, Views.DetailedRide.class })
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_activated")
    private boolean isActivated = false;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "contact_by_sms")
    private boolean contactBySms = false;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "contact_by_mail")
    private boolean contactByMail = false;

    @JsonView(value = { Views.DetailedUser.class, Views.DetailedRide.class })
    @Column
    private String photoPath;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "start_activity")
    private LocalDate startActivity;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "end_activity")
    private LocalDate endActivity;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    private List<Notification> notifications;

    @JsonView(Views.DetailedUser.class)
    @OneToMany(mappedBy = "person")
    private List<Car> cars;

    @JsonBackReference
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<RidePassenger> rides = new ArrayList<RidePassenger>();

    @JsonView(Views.SimpleUser.class)
    @Column(name = "person_type", nullable = false, insertable = false, updatable = false)
    private String personType;

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDate getStartActivity() {
        return startActivity;
    }

    public void setStartActivity(LocalDate startActivity) {
        this.startActivity = startActivity;
    }

    public LocalDate getEndActivity() {
        return endActivity;
    }

    public void setEndActivity(LocalDate endActivity) {
        this.endActivity = endActivity;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<RidePassenger> getRides() {
        return rides;
    }

    public void setRides(List<RidePassenger> rides) {
        this.rides = rides;
    }

    public Person() {
    }

    @JsonView(Views.SimpleUser.class)
    public String getShowedName() {
        return this.firstName + " " + this.surname.charAt(0) + ".";
    }

    /**
     * Renvoie les "authorités" de l'utilisateur
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // l'utilisateur est forcément "USER"
        // Bien ré-implémenter "getAuthorities" dans toutes les classes filles
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Permet de retourner une liste d'autorités sous la forme de chaîne de caractères.
     * @return Liste de chaînes de caractères représentant les autorités
     */
    public List<String> getStringAuthorities() {
        return getAuthorities().stream().map(authority -> authority.getAuthority()).toList();
    }

    /**
     * Username == email.
     * C'est comme ça (et dû au fait que la notion de "username" est utilisée par Spring Security pour gérer la connexion)
     * 
     * Pour toute réclamation contacter une des personnes suivantes : https://spring.io/authors
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActivated;
    }
}
