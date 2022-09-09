package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "recurring")
@DiscriminatorValue("R")
public class RecurringRide extends Ride {

    @Column
    private LocalDate beginning;

    @Column
    private LocalDate ending;

    @JoinTable( name = "recurring_days", joinColumns = @JoinColumn(name = "id_ride"), inverseJoinColumns = @JoinColumn(name = "id_day_week"))
    @JsonManagedReference
    @ManyToMany
    private Set<DayWeek> daysWeek;

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

    public Set<DayWeek> getDaysWeek() {
        return daysWeek;
    }

    public void setDaysWeek(Set<DayWeek> daysWeek) {
        this.daysWeek = daysWeek;
    }

    public RecurringRide() {
    }
}