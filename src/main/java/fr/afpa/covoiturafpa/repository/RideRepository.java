package fr.afpa.covoiturafpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.City;
import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.Destination;
import fr.afpa.covoiturafpa.model.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer> {

    // @Query("SELECT ride FROM Ride ride JOIN RecurringRide WHERE ((:destination.city.name IN (SELECT d.city.name FROM Destination d WHERE d.city IS NOT NULL)) OR (get_distance(:destination.latitude, :destination.longitude, ride.destination.latitude, ride.destination.longitude)))")
    // public List<Ride> findRecurringRides(@Param("destination") Destination destination);
   

    // @Query("SELECT ride FROM Ride ride JOIN OneTimeRide WHERE ((:#{#destination.city.name} IN (SELECT d.city.name FROM Destination d WHERE d.city IS NOT NULL)) OR (get_distance(:#{#destination.latitude}, :#{#destination.longitude}, ride.destination.latitude, ride.destination.longitude))) AND ride.departureDay = :date")
    // public List<Ride> findOneTimeRides(@Param("destination") Destination destination, @Param("date") LocalDate date);

    @Query("SELECT d FROM Destination d WHERE function('get_distance', d.latitude, d.longitude, :a, :b) < 5")
    public List<Destination> findTest(@Param("a") double a, @Param("b") double b);

    @Query("SELECT ride FROM Person user JOIN RidePassenger rp ON user.id = rp.id.idPerson JOIN Ride ride ON rp.id.idRide = ride.id WHERE rp.id.idPerson = :id")
    public Iterable<Ride> findRidesOfPerson(@Param("id") int id);
}