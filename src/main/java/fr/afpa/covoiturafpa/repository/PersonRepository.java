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
    
    @Query(value = "SELECT * FROM person JOIN trainee ON person.id_person = trainee.id_person WHERE trainee.id_formation = :idFormation", nativeQuery = true)
    public Iterable<Person> findByIdFormation(@Param("idFormation") int idFormation);

    @Query(value="DELETE FROM Person usr WHERE AGE(usr.lastLogin, current_date) > '6 months'")
    public void deleteInactiveForSixMonths();

    @Query(value="UPDATE Employee emp SET emp.isAdmin = TRUE WHERE emp.id = :id")
    public Employee setAdmin(@Param("id") Integer id);

    @Query(value="SELECT per FROM Person per WHERE per.email = :email")
    public Optional<Person> findByEmail(@Param("email") String email);
}