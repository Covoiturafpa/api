package fr.afpa.covoiturafpa.utils.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.PersonRepository;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personWithUsername = personRepository.findByEmail(username);
        org.springframework.security.core.userdetails.User user = null;
        if (personWithUsername.isPresent()) {
            Person foundPerson = personWithUsername.get();
            user = new org.springframework.security.core.userdetails.User(
                foundPerson.getEmail(), 
                foundPerson.getPassword(), 
                foundPerson.getAuthorities()
            );
            return user;
        }
        else {
            throw new UsernameNotFoundException("No person found with this email");
        }
    }
}
