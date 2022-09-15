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
            User foundUser = userWithUsername.get();
            person = new org.springframework.security.core.userdetails.User(
                foundUser.getEmail(), 
                foundUser.getPassword(), 
                foundUser.getAuthorities()
            );
            return person;
        }
        else {
            throw new UsernameNotFoundException("No user found with this email");
        }
    }
}
