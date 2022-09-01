package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

public class OneTimeRide extends Ride {
    private int id;
    private LocalDate departureDay;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDepartureDay() {
        return departureDay;
    }
    
    public void setDepartureDay(LocalDate departureDay) {
        this.departureDay = departureDay;
    }

    public OneTimeRide() {
    }
}
