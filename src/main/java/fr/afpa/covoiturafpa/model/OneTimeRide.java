package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonTypeName("O")
@Entity
@Table(name = "one_time")
@DiscriminatorValue("O")
public class OneTimeRide extends Ride {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class) 
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
