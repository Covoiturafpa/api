package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;
import java.util.Set;

public class RecurringRide extends Ride {
    private int id;
    private LocalDate beginning;
    private LocalDate ending;
    private Set<Day> days;
}