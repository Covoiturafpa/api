package fr.afpa.covoiturafpa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import fr.afpa.covoiturafpa.model.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer> {

    @Query("SELECT OneTimeRide ride WHERE ride.departureDay = :date")
    public List<Ride> findOneTimeRidesByDate(@Param("date") LocalDate date);

    // @Query("SELECT RecurringRide ride WHERE ride.beginning = :date")
    // public List<Ride> findRecurringRidesByDate(@Param("date") LocalDate date);
}