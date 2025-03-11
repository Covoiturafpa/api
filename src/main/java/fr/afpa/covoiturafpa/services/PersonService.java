package fr.afpa.covoiturafpa.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.utils.PersonInfoChecker;

/**
 * Classe regroupant la logique métier permettant de traiter les objets de la classe "Person"
 *
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isValidNewPerson(Person newPerson) {
        return (PersonInfoChecker.hasValidFields(newPerson) && personRepository.findByEmail(newPerson.getEmail()).isEmpty());
    }

    /**
     * Création d'un utilisateur en base de données
     * @param newPerson
     * @return
     */
    public Person createPerson(Person newPerson) {
        newPerson.setPassword(passwordEncoder.encode(newPerson.getPassword()));
        return personRepository.save(newPerson);
    }

    public Optional<Person> findPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Optional<Person> findPersonById(Integer id) {
        return personRepository.findById(id);
    }
}
