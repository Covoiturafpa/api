package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;
import java.util.Set;

public class RecurringRide extends Ride {
    private int id;
    private LocalDate beginning;
    private LocalDate ending;
    private Set<Day> days;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalDate beginning) {
        this.beginning = beginning;
    }

    public LocalDate getEnding() {
        return ending;
    }

    public void setEnding(LocalDate ending) {
        this.ending = ending;
    }

    public Set<Day> getDays() {
        return days;
    }
    
    public void setDays(Set<Day> days) {
        this.days = days;
    }

    public RecurringRide() {
    }
}