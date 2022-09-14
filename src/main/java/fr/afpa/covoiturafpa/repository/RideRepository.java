package fr.afpa.covoiturafpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.Destination;
import fr.afpa.covoiturafpa.model.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer> {

    // @Query("SELECT ride FROM Ride ride JOIN OneTimeRide WHERE ((:destination.city IN (SELECT d.city FROM Destination d WHERE d.city IS NOT NULL)) OR (distance()))")
    // public List<Ride> findOneTimeRides(@Param("destination") Destination destination, @Param("date") LocalDate date);

    // @Query("SELECT ride FROM RecurringRide")
    // public List<Ride> findRecurringRides(@Param("destination") Destination destination, @Param("start") LocalDate start, @Param("end") LocalDate end, @Param("days") Set<DayWeek> days);


    @Query("SELECT ride FROM RecurringRide ride WHERE ride.beginning = :date")
    public List<Ride> findRecurringRidesByDate(@Param("date") LocalDate date);

    @Query("SELECT rde FROM User usr JOIN RidePassenger rp ON usr.id = rp.id.idUser JOIN Ride rde ON rp.id.idRide = rde.id WHERE rp.id.idUser = :id")
    public Iterable<Ride> rideToUser(@Param("id") int id);
}