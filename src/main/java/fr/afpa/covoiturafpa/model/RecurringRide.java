package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonTypeName("R")
@Entity
@Table(name = "recurring", schema = "covoiturafpa")
@DiscriminatorValue("R")
public class RecurringRide extends Ride {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonView(Views.SimpleRide.class)
    @Column
    private LocalDate beginning;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonView(Views.SimpleRide.class)
    @Column
    private LocalDate ending;

    @JsonView(Views.SimpleRide.class)
    @JoinTable(name = "recurring_days", joinColumns = @JoinColumn(name = "id_ride"), inverseJoinColumns = @JoinColumn(name = "id_day_week"))
    @ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<DayWeek> daysWeek = new ArrayList<DayWeek>();

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

    public List<DayWeek> getDaysWeek() {
        return daysWeek;
    }

    public void setDaysWeek(List<DayWeek> daysWeek) {
        this.daysWeek = daysWeek;
    }

    public RecurringRide() {
    }

    public RecurringRide(Destination destination, LocalDate beginning, LocalDate ending, List<DayWeek> daysWeek) {
        super(destination);
        this.beginning = beginning;
        this.ending = ending;
        this.daysWeek = daysWeek;
    }
}