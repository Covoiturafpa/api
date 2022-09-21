package fr.afpa.covoiturafpa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer>, RideRepositoryCustom {

    @Query(value = "SELECT * FROM recurring AS rr LEFT JOIN ride AS r ON rr.id_ride = r.id_ride JOIN destination AS d ON r.id_destination = d.id_destination JOIN city AS c ON d.id_city = c.id_city WHERE (( c.name = :cityName AND c.name IS NOT NULL) OR get_distance(:latitude, :longitude, d.latitude, d.longitude) <= 5) AND (:start <= rr.ending AND rr.beginning <= :end) AND d.is_from_afpa = :isFromAfpa AND r.is_active", nativeQuery = true)
    public List<Ride> findRecurringRides(@Param("cityName") String cityName, @Param("latitude") double latitude, @Param("longitude") double longitude, @Param("start") LocalDate RequestedStart, @Param("end") LocalDate RequestedEnd, @Param("isFromAfpa") boolean isFromAfpa);
   

    @Query(value = "SELECT * FROM one_time AS ot LEFT JOIN ride AS r ON ot.id_ride = r.id_ride JOIN destination AS d ON r.id_destination = d.id_destination JOIN city AS c ON d.id_city = c.id_city WHERE (( c.name = :cityName AND c.name IS NOT NULL) OR get_distance(:latitude, :longitude, d.latitude, d.longitude) <= 5) AND ot.departure_day = :departureDay AND d.is_from_afpa = :isFromAfpa AND r.is_active", nativeQuery = true)
    public List<Ride> findOneTimeRides(@Param("cityName") String cityName, @Param("latitude") double latitude, @Param("longitude") double longitude, @Param("departureDay") LocalDate departureDay, @Param("isFromAfpa") boolean isFromAfpa);


    @Query("SELECT ride FROM Person user JOIN RidePassenger rp ON user.id = rp.id.idPerson JOIN Ride ride ON rp.id.idRide = ride.id WHERE rp.id.idPerson = :id")
    public Iterable<Ride> findRidesOfPerson(@Param("id") int id);
}