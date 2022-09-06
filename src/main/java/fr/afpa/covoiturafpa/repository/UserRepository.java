package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
}
