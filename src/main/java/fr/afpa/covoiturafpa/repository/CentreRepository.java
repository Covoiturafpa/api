package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.afpa.covoiturafpa.model.Centre;

@Repository
public interface CentreRepository extends CrudRepository<Centre, Integer> {
    
}