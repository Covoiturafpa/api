package fr.afpa.covoiturafpa.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;

import java.lang.StackWalker.Option;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.Car;
import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.CarRepository;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.utils.Views;
import fr.afpa.covoiturafpa.repository.PersonRepository;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @JsonView(Views.SimpleUser.class)
    @CrossOrigin
    @GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> list() {
        return personRepository.findAll();
    }

    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> get(@PathVariable(required = true) Integer id) {
        return personRepository.findById(id);
    }

    @JsonView(Views.DetailedRide.class)
    @CrossOrigin
    @GetMapping(value = "/users/{id}/rides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> getRidesOfPerson(@PathVariable(required = true) Integer id) {
        return rideRepository.findRidesOfPerson(id);

    }

    @CrossOrigin
    @GetMapping(value = "/users/{id}/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Set<Notification> getNotifications(@PathVariable(required = true) int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get().getNotifications();
        }
        return null;
    }

    @CrossOrigin
    @PutMapping(value = "/users/{id}/notiications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Set<Notification> setAsReadNotifications(@PathVariable(required = true) int id) {
        return notificationRepository.updateAllUnreadByPerson(id);
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
    public void deleteInactivePersonsForSixMonths() {
        //personRepository.deleteInactiveForSixMonths();
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) int id) {
        Optional<Person> optPerson = personRepository.findById(id);
        if (optPerson.isPresent()) {
            Person person = optPerson.get();
            personRepository.delete(person);
        }
    }
    
    // TODO: {id}/cars
    @CrossOrigin
    @PostMapping(value = "/users/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Car createCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            car.setPerson(person.get());
            return carRepository.save(car);
        }
        return null;
    }
    
}
