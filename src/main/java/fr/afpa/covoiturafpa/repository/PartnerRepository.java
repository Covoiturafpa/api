package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Partner;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Integer> {
    
}