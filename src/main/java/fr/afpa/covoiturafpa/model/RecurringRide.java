package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "recurring")
@DiscriminatorValue("R")
public class RecurringRide extends Ride {

    @Column
    private LocalDate beginning;

    @Column
    private LocalDate ending;

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

    public RecurringRide() {
    }
}