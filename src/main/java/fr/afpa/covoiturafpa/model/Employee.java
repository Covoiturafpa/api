package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

public class Employee extends User {
    private int id;
    private String role;
    private boolean isAdmin;
    private LocalDate startContract;
    private LocalDate endContract;
    private Centre centre;
}
