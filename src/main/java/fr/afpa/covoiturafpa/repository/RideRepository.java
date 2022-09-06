package fr.afpa.covoiturafpa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.OneTimeRide;
import fr.afpa.covoiturafpa.model.RecurringRide;
import fr.afpa.covoiturafpa.model.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer> {

    @Query("SELECT OneTimeRide ride WHERE ride.departureDay = ?1")
    public List<OneTimeRide> findOneTimeRidesByDate(LocalDate date);

    @Query("SELECT RecurringRide ride WHERE ride.beginning = ?1")
    public List<RecurringRide> findRecurringRidesByDate(LocalDate date);
}
