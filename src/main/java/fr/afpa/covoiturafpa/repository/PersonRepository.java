package fr.afpa.covoiturafpa.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import fr.afpa.covoiturafpa.model.Employee;
import fr.afpa.covoiturafpa.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query(value="DELETE FROM Person usr WHERE AGE(usr.lastLogin, current_date) > '6 months'")
    public void deleteInactiveForSixMonths();

    @Query(value="UPDATE Employee emp SET emp.isAdmin = TRUE WHERE emp.id = :id")
    public Employee setAdmin(@Param("id") Integer id);

    public Optional<Person> findByEmail(String email);
}