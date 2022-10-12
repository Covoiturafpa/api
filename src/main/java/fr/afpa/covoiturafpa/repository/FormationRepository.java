package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Formation;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Integer> {
   
}
