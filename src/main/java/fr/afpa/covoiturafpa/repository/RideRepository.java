package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer> {
    
}
