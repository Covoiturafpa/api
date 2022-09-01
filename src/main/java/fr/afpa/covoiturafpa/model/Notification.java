package fr.afpa.covoiturafpa.model;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String type;
    private LocalDateTime createdTime;
    private boolean isUnread;
}
