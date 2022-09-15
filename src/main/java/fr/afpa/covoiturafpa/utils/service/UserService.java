package fr.afpa.covoiturafpa.utils.service;

import java.util.List;
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
        Optional<User> userWithUsername = userRepository.findByEmail(username);
        if (userWithUsername.isPresent()) {
            return userWithUsername.get();
        }
        else {
            throw new UsernameNotFoundException("No user found with this email");
        }
    }
}
