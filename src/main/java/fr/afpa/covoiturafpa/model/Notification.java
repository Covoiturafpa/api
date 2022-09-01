package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private TypeNotif type;
    private LocalDateTime createdTime;
    private boolean isUnread;

    enum TypeNotif {
        NEW_RESERVATION,
        REJECTED_RESERVATION,
        ACCEPTED_RESERVATION,
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

    public boolean isUnread() {
        return isUnread;
    }

    public void setUnread(boolean isUnread) {
        this.isUnread = isUnread;
    }

    public Notification() {
    }
}
