package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "recurring")
public class RecurringRide extends Ride {

    @Column
    private LocalDate beginning;

    @Column
    private LocalDate ending;

    @Column
    private Set<Day> days;


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