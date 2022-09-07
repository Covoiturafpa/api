package fr.afpa.covoiturafpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Employee;
import fr.afpa.covoiturafpa.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value="DELETE FROM User usr WHERE AGE(usr.lastLogin, current_date) > '6 months'")
    public void deleteInactiveForSixMonths();

    @Query(value="UPDATE Employee emp SET emp.isAdmin = TRUE WHERE emp.id = :id")
    public Employee setAdmin(@Param("id") Integer id);
}