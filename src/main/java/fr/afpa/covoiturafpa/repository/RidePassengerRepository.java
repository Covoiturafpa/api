package fr.afpa.covoiturafpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.RidePassenger;

@Repository
public interface RidePassengerRepository extends CrudRepository<RidePassenger, Integer> {

    @Query("SELECT rp FROM RidePassenger rp WHERE rp.person = :person AND rp.ride = :ride")
    public Optional<RidePassenger> findByPersonAndRide(@Param("person") Person person, @Param("ride") Ride ride);
}
