package fr.afpa.covoiturafpa.model;

import java.time.DayOfWeek;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonIdentityInfo(generator=ObjectIdGenerators.None.class, property="idDayWeek")
@Entity
@Table(name = "day_week", schema = "covoiturafpa")
public class DayWeek {

    @JsonView(Views.SimpleRide.class)
    @Id
    @Column(name= "id_day_week")
    private Integer idDayWeek;

    @JsonView(Views.SimpleRide.class)
    @Enumerated(EnumType.STRING)
    @Column
    private DayOfWeek name;

    @JsonIgnore
    @ManyToMany(mappedBy = "daysWeek")
    private List<RecurringRide> rides;

    public List<RecurringRide> getRides() {
        return rides;
    }

    public void setRides(List<RecurringRide> rides) {
        this.rides = rides;
    }

    public Integer getIdDayWeek() {
        return idDayWeek;
    }

    public void setIdDayWeek(Integer idDayWeek) {
        this.idDayWeek = idDayWeek;
    }

    public DayOfWeek getName() {
        return name;
    }

    public void setName(DayOfWeek name) {
        this.name = name;
        this.idDayWeek = name.getValue();
    }
    
    public DayWeek() {
    }

    public DayWeek(DayOfWeek day) {
        this.idDayWeek = day.getValue();
        this.name = day;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDayWeek == null) ? 0 : idDayWeek.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DayWeek other = (DayWeek) obj;
        if (idDayWeek == null) {
            if (other.idDayWeek != null)
                return false;
        } else if (!idDayWeek.equals(other.idDayWeek))
            return false;
        return true;
    }
}
