package fr.afpa.covoiturafpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import fr.afpa.covoiturafpa.model.Destination;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Integer>{
    
}
