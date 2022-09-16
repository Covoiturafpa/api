package fr.afpa.covoiturafpa.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;

import java.lang.StackWalker.Option;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.covoiturafpa.model.Car;
import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.RidePassenger;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.CarRepository;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @CrossOrigin
    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> list() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/users/{id}/rides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> getRidesToUser(@PathVariable(required = true) Integer id) {
        return rideRepository.rideToUser(id);

    }

    @CrossOrigin
    @GetMapping(value = "/users/{id}/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Set<Notification> getNotifications(@PathVariable(required = true) int id) {
        Optional<Person> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getNotifications();
        }
        return null;
    }

    // TODO: POST
    @CrossOrigin
    @PostMapping(value = "/users", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Person create() {
        return null;
    }

    // TODO: {id} PUT
    @CrossOrigin
    @PutMapping(value = "/users/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Person update(@PathVariable(required = true) Integer id) {
        return null;
    }

    @CrossOrigin
    @DeleteMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInactiveUsersForSixMonths() {
        //userRepository.deleteInactiveForSixMonths();
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) int id) {
        Optional<Person> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            Person user = optUser.get();
            userRepository.delete(user);
        }
    }
    
    // TODO: {id}/cars
    @CrossOrigin
    @PostMapping(value = "/users/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Car createCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        Optional<Person> user = userRepository.findById(id);
        if (user.isPresent()) {
            car.setUser(user.get());
            return carRepository.save(car);
        }
        return null;
    }
    


    // TODO: {id}/notifications
    @CrossOrigin
    @GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> get(@PathVariable(required = true) Integer id) {
        return userRepository.findById(id);
    }

}
