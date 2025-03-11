package fr.afpa.covoiturafpa.services.authentication;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.afpa.covoiturafpa.controllers.CentreController;
import fr.afpa.covoiturafpa.dto.LoginUserDto;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.PersonRepository;

/**
 * Service gérant la connexion d'un utilisateur
 */
@Service
public class AuthenticationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Construction utilisé pour l'injection de dépendances.
     * 
     * @param personRepository Permet d'effectuer la persistance d'un nouvel utilisateur en base de données
     * @param authenticationManager Utilisé pour vérifier qu'un utilisateur peut se connecter
     * @param passwordEncoder Utilisé pour encoder les mots de passe lors de la création d'un nouvel utilisateur.
     */
    public AuthenticationService(
        PersonRepository personRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Permet d'enregistrer un nouvel utilisateur.
     * @param user l'utilisateur à créer en base de données
     */
    public Optional<Person> register(Person user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Person newUser = null;
        try {
            newUser = personRepository.save(user);
        } catch (DataIntegrityViolationException constraintViolation) {
            System.err.println(constraintViolation.getMessage());
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return Optional.of(newUser);
    }

    /**
     * Méthode utilisé pour tenter d'authentifier un utilisateur.
     * Dans le cas d'un utilisateur inexistant la fonction lance une exception NoSuchElementException
     * 
     * @param input
     * @return Une instance de l'utilisateur authentifié
     */
    public Person login(LoginUserDto input) {
        
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
            );
        } catch(Exception e) {
            Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
            logger.info(e.getMessage());
        }

        return personRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}