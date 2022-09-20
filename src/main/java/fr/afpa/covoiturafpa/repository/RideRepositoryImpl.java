package fr.afpa.covoiturafpa.repository;

import java.util.List;
import java.util.Set;

import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.RecurringRide;
import fr.afpa.covoiturafpa.model.Ride;

public class RideRepositoryImpl implements RideRepositoryCustom{

	@Override
	public List<Ride> filterRecurringRidesByDays(List<Ride> possibleRides, Set<DayWeek> days) {
		possibleRides.removeIf(ride -> !(((RecurringRide) ride).getDaysWeek().containsAll(days)));
        return possibleRides;
	}
    
    
}
