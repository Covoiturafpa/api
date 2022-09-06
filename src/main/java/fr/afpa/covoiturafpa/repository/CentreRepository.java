package fr.afpa.covoiturafpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Centre;
import fr.afpa.covoiturafpa.model.Partner;

@Repository
public interface CentreRepository extends CrudRepository<Centre, Integer> {

    @Query(value = "SELECT p FROM Centre c JOIN Partner p ON c.id_centre = p.id_centre WHERE id_centre = ?1")
    public Iterable<Partner> findPartners(int id);

    @Query(value = "INSERT INTO Partner p (name, logo_picture_path, id_centre) VALUES (?1, ?2, ?3)")
    public Optional<Partner> savePartner(String name, String logo_picture_path, int id_centre);

    @Query(value = "DELETE Partner p WHERE id_partner = ?1")
    public void deletePartnerById(int id);
}
