package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "notification", schema = "covoiturafpa")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column
    private TypeNotif type;
    
    @Column(name = "created_time")
    private LocalDateTime createdTime = LocalDateTime.now();

    @Column(name = "is_unread")
    private boolean isUnread = true;

    @Column
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_person")
    private Person person;

    public enum TypeNotif {
        NEW_RESERVATION,
        REJECTED_RESERVATION,
        ACCEPTED_RESERVATION,
        NEW_TRAINEE,
        NEW_EMPLOYEE
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

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

    public Person getPerson() {
    return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Notification() {
    }

    public Notification(TypeNotif type, String content, Person person) {
        this.type = type;
        this.content = content;
        this.person = person;
    }
}
