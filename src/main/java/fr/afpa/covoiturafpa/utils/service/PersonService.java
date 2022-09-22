package fr.afpa.covoiturafpa.utils.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.utils.security.CustomUserDetails;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personWithUsername = personRepository.findByEmail(username);
        CustomUserDetails user = null;
        if (personWithUsername.isPresent()) {
            Person foundPerson = personWithUsername.get();
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            user = new CustomUserDetails(
                foundPerson.getId(),
                foundPerson.getEmail(), 
                foundPerson.getPassword(), 
                foundPerson.getIsActivated(),
                authorities
            );
            
            return user;
        }
        else {
            throw new UsernameNotFoundException("No person found with this email");
        }
    }
}
