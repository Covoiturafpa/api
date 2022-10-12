package fr.afpa.covoiturafpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import fr.afpa.covoiturafpa.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer>{
    
}
