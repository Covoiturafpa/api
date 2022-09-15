package fr.afpa.covoiturafpa.utils.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.afpa.covoiturafpa.model.User;
import fr.afpa.covoiturafpa.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(45);
        if (user.isPresent()) {
            // attention gestion authorities
            
            return user.get();
        }  else {
            // gestion de l'erreur
            // message de log
            return null;
        }
        
    }
}
