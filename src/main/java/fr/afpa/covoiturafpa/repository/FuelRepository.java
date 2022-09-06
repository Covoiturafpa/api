package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Fuel;

@Repository
public interface FuelRepository extends CrudRepository<Fuel, Integer> {
    
}