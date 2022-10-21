package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
    
}
