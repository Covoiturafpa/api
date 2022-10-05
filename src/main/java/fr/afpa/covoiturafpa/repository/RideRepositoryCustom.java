package fr.afpa.covoiturafpa.repository;

import java.util.List;

import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.Ride;


public interface RideRepositoryCustom {
    public List<Ride> filterRecurringRidesByDays(List<Ride> possibleRides, List<DayWeek> days);
}
