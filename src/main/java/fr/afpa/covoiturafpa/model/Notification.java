package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column
    private TypeNotif type;
    
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "is_unread")
    private boolean isUnread;

    @ManyToOne
    @JsonBackReference 
    @JoinColumn(name="id_person")
    private Person user;

    enum TypeNotif {
        NEW_RESERVATION,
        REJECTED_RESERVATION,
        ACCEPTED_RESERVATION,
    }
    
    //TODO: retrouver les variables
    @JsonSerialize
    public String getContent() {
        String content = "";
        switch(this.type) {
            case NEW_RESERVATION:
                content = "Bonjour, " + this.type + " est intéressé.e par votre trajet " + this.type + ". Vous pouvez l’appeler au " + this.type + " pour vous organiser.";
                break;
            case REJECTED_RESERVATION:
                content = "Bonjour, malheureusement " + this.type + " n’a pas accepté votre demande de trajet. D’autres sont sûrement disponibles !";
                break;
            case ACCEPTED_RESERVATION:
                content = "Bonjour, " + this.type + " vient d’accepter votre demande de trajet. Bon covoiturage !";
                break;
        }
        return(content);
    }

    // public void setContentString(String contentString) {
    //     this.contentString = contentString;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeNotif getType() {
        return type;
    }

    public void setType(TypeNotif type) {
        this.type = type;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean getIsUnread() {
        return isUnread;
    }

    public void setIsUnread(boolean isUnread) {
        this.isUnread = isUnread;
    }

    public Notification() {
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }
}
