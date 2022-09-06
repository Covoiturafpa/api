package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;

import fr.afpa.covoiturafpa.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
