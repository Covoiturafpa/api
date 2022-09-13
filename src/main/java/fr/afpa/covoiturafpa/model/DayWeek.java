package fr.afpa.covoiturafpa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "day_week")
public class DayWeek {

    @Id
    @Column(name= "id_day_week")
    private Integer idDayWeek;

    
    @Enumerated(EnumType.STRING)
    @Column
    private Day name;

    @JsonBackReference
    @ManyToMany(mappedBy = "daysWeek")
    private Set<RecurringRide> rides;

    public Set<RecurringRide> getRides() {
        return rides;
    }

    public void setRides(Set<RecurringRide> rides) {
        this.rides = rides;
    }

    public Integer getIdDayWeek() {
        return idDayWeek;
    }

    public void setIdDayWeek(Integer idDayWeek) {
        this.idDayWeek = idDayWeek;
    }

    public Day getName() {
        return name;
    }

    public void setName(Day name) {
        this.name = name;
    }
    public DayWeek() {

    }
}
