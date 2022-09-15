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
        org.springframework.security.core.userdetails.User user = null;
        if (userWithUsername.isPresent()) {
            // if (userWithUsername.get() instanceof Trainee) {
            //     Trainee trainee = new Trainee();
            //     return trainee = ((Trainee)userWithUsername).get();
            // }
            // else {
            //     Employee employee = new Employee();
            //     return employee = ((Employee)userWithUsername).get();
            // }
            user = new org.springframework.security.core.userdetails.User(userWithUsername.get().getEmail(), userWithUsername.get().getPassword(), userWithUsername.get().getAuthorities());
            return user;
        }
        else {
            throw new UsernameNotFoundException("No user found with this email");
        }
    }
}
