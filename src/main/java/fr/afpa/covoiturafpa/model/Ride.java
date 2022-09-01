package fr.afpa.covoiturafpa.model;

import java.time.LocalTime;

public class Ride {
    private int id;
    private LocalTime departureTime;
    private boolean isActive;
    private String comment;
    private Destination destination;

    public int countFreeSeats() {
        return 0;
    }
}