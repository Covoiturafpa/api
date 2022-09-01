package fr.afpa.covoiturafpa.model;

public class NotifConfig {
    private int id;
    private boolean contactBySms;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isContactBySms() {
        return contactBySms;
    }

    public void setContactBySms(boolean contactBySms) {
        this.contactBySms = contactBySms;
    }

    public NotifConfig() {
    }
}
