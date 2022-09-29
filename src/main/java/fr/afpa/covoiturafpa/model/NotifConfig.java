package fr.afpa.covoiturafpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "notif_config")
public class NotifConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notif_config")
    private int id;

    @Column(name = "contact_by_sms")
    private boolean contactBySms;

    @JsonBackReference
    @OneToOne(mappedBy = "notifConfig")
    private Centre centre;


    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getContactBySms() {
        return contactBySms;
    }

    public void setContactBySms(boolean contactBySms) {
        this.contactBySms = contactBySms;
    }

    public NotifConfig() {
    }
}
