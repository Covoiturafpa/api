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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonTypeName("R")
@Entity
@Table(name = "recurring")
@DiscriminatorValue("R")
public class RecurringRide extends Ride {

    @JsonView(Views.SimpleRide.class)
    @Column
    private LocalDate beginning;

    @JsonView(Views.SimpleRide.class)
    @Column
    private LocalDate ending;

    @JsonView(Views.SimpleRide.class)
    @JoinTable(name = "recurring_days", joinColumns = @JoinColumn(name = "id_ride"), inverseJoinColumns = @JoinColumn(name = "id_day_week"))
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

    public RecurringRide(Destination destination, LocalDate beginning, LocalDate ending, Set<DayWeek> daysWeek) {
        super(destination);
        this.beginning = beginning;
        this.ending = ending;
        this.daysWeek = daysWeek;
    }

    public boolean hasDays(Set<DayWeek> days) {
        return false;
    }
}