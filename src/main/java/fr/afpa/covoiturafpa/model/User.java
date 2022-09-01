package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class User {
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
    private List<Notification> notifications;
}
