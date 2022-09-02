package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    
}
