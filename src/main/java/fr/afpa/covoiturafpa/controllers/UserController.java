package fr.afpa.covoiturafpa.controllers;

import org.springframework.http.MediaType;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.covoiturafpa.model.User;
import fr.afpa.covoiturafpa.repository.CarRepository;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @CrossOrigin
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> get(@PathVariable(required = true) Integer id) {
        return userRepository.findById(id);
    }

    // TODO: POST
    @CrossOrigin
    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public User create() {
        return null;
    }

    // TODO: {id} PUT
    @CrossOrigin
    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable(required = true) Integer id) {
        return null;
    }

    @CrossOrigin
    @DeleteMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInactiveUsersForSixMonths() {
        userRepository.deleteInactiveForSixMonths();
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) int id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            User user = optUser.get();
            userRepository.delete(user);
        }
    }
    
    // TODO: {id}/cars 

    // TODO: {id}/notifications
    
    // TODO: {id}/rides
}
