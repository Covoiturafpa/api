package fr.afpa.covoiturafpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.afpa.covoiturafpa.model.Centre;

@Repository
public interface CentreRepository extends CrudRepository<Centre, Integer> {
    
    @Query(value="SELECT DISTINCT COUNT(c) FROM Centre c")
    public int countAll();
}