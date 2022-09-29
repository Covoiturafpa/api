package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonTypeName("O")
@Entity
@Table(name = "one_time")
@DiscriminatorValue("O")
public class OneTimeRide extends Ride {

    @JsonView(Views.SimpleRide.class)
    @Column(name = "departure_day")
    private LocalDate departureDay;


    public LocalDate getDepartureDay() {
        return departureDay;
    }
    
    public void setDepartureDay(LocalDate departureDay) {
        this.departureDay = departureDay;
    }

    public OneTimeRide() {
    }
}
