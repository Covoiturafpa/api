package fr.afpa.covoiturafpa.utils.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.afpa.covoiturafpa.model.Employee;
import fr.afpa.covoiturafpa.model.Trainee;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.UserRepository;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> userWithUsername = userRepository.findByEmail(username);
        org.springframework.security.core.userdetails.User person = null;
        if (userWithUsername.isPresent()) {
            Person foundPerson = userWithUsername.get();
            person = new org.springframework.security.core.userdetails.User(
                foundPerson.getEmail(), 
                foundPerson.getPassword(), 
                foundPerson.getAuthorities()
            );
            return person;
        }
        else {
            throw new UsernameNotFoundException("No person found with this email");
        }
    }
}
