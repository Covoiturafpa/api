package fr.afpa.covoiturafpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Centre;
import fr.afpa.covoiturafpa.model.Partner;

@Repository
public interface CentreRepository extends CrudRepository<Centre, Integer> {

}
