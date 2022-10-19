package fr.afpa.covoiturafpa.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.Car;
import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.utils.NotifContentBuilder;
import fr.afpa.covoiturafpa.model.utils.Views;
import fr.afpa.covoiturafpa.repository.CarRepository;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.utils.hcaptcha.PersonCreationRequest;
import fr.afpa.covoiturafpa.utils.security.CustomUsernamePasswordAuthenticationToken;
import fr.afpa.covoiturafpa.utils.security.JwtUtil;

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

    @Autowired
    private ApplicationContext context;

    
    @JsonView(Views.SimpleUser.class)
    @CrossOrigin
    @Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
    @GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> list() {
        return personRepository.findAll();
    }
     
    @CrossOrigin
    @PostMapping(value = "/users", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody PersonCreationRequest personCreationRequest) {
        String captchaToken = personCreationRequest.getCaptchaToken().getToken();
        if (true) {
            Person newPerson = personCreationRequest.getPerson();
            newPerson.setPassword(context.getBean(PasswordEncoder.class).encode(newPerson.getPassword()));
            return personRepository.save(newPerson);
        }
        return null;
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin
    @DeleteMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInactivePersonsForSixMonths() {
        //TODO: personRepository.deleteInactiveForSixMonths();
    }

    @CrossOrigin
    @GetMapping(value = "/users/username/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> getByEmail(@PathVariable(required = true) String username) {
        return personRepository.findByEmail(username);
    }

    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> get(@PathVariable(required = true) Integer id) {
        return personRepository.findById(id);
    }
    
    @CrossOrigin
    @PatchMapping(value = "/users/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Person update(@RequestBody Person person) {
        return personRepository.save(person);
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

    @JsonView(Views.DetailedRide.class)
    @CrossOrigin
    @GetMapping(value = "/users/{id}/rides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> getRidesOfPerson(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization, @PathVariable(required = true) Integer id) {
        try {
            String[] tokenArray = headerAuthorization.split(" ");
            CustomUsernamePasswordAuthenticationToken userAuthentication = JwtUtil.parseToken(tokenArray[1]);
            if (userAuthentication.getIdUser().equals(id)) {
                return rideRepository.findRidesByPerson(id);
            }
        }catch(Exception e) {
            Logger logger = LoggerFactory.getLogger(PersonController.class);
            logger.error("Erreur lors de la conception de la réponse JSon" + e);
        }
        return null;
    }

    @CrossOrigin
    @PatchMapping(value = "/users/{idPerson}/rides/{idRide}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRide() {
        //TODO: suivre trello, ou pas ?
    }

    @CrossOrigin
    @PutMapping(value = "/users/{idDriver}/rides/{idRide}")
    @ResponseStatus(HttpStatus.OK)
    public void manageReservation(@PathVariable(required = true) Integer idRide, @RequestParam Integer idPassenger, @RequestParam boolean isAccepted) {
        Ride ride = rideRepository.findById(idRide).get();
        Person passenger = personRepository.findById(idPassenger).get();
        rideRepository.save(ride.manageBooking(passenger, isAccepted));
        saveBookingNotification(ride, passenger, isAccepted);
    }

    public void saveBookingNotification(Ride ride, Person passenger, boolean isAccepted) {
        if (isAccepted) {
            Notification newNotification = new Notification(Notification.TypeNotif.ACCEPTED_RESERVATION, NotifContentBuilder.createAcceptedBookingContent(ride), passenger);
            notificationRepository.save(newNotification);
        }
        else {
            Notification newNotification = new Notification(Notification.TypeNotif.REJECTED_RESERVATION, NotifContentBuilder.createRejectedBookingContent(ride), passenger);
            notificationRepository.save(newNotification);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/users/{id}/cars", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Car createCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            car.setPerson(person.get());
            return carRepository.save(car);
        }
        return null;
    }

    @CrossOrigin
    @PatchMapping(value = "/users/{id}/cars", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Car updateCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        if (car.getPerson().getId() == id) {
            return carRepository.save(car);
        }
        return null;
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{id}/cars", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void deleteCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        if (car.getPerson().getId() == id) {
            carRepository.delete(car);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/users/{id}/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Notification> getNotifications(@PathVariable(required = true) int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get().getNotifications();
        }
        return null;
    }

    @CrossOrigin
    @PutMapping(value = "/users/{id}/notifications", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public int setAsReadNotifications(@PathVariable(required = true) int id) {
        return notificationRepository.updateAllUnreadByPerson(id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{id}/notifications")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllNotification(@PathVariable(required = true) int id) {
        notificationRepository.deleteAllByPerson(personRepository.findById(id).get());
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{idUser}/notifications", params = "idNotification")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotificationById(@PathVariable(required = true) Integer idUser, @RequestParam Integer idNotification) {
        notificationRepository.deleteByIdAndPerson(idNotification, personRepository.findById(idUser).get());
    }

    @CrossOrigin
    @GetMapping(value = "/users/{id}/new_notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public boolean checkNewNotifications(@PathVariable(required = true) int id) {
        return (notificationRepository.countNewNotifications(id) > 0);
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin
    @PatchMapping(value = "/users/{id}/roles", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Person giveAdminOrTeacherAccess(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @Secured({"ROLE_ADMIN","ROLE_TEACHER"})
    @CrossOrigin
    @PatchMapping(value = "/users/{id}/activation", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Person activateAccount(@PathVariable(required = true) int id, @RequestBody Person user) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            person.get().setIsActivated(true);
            return personRepository.save(person.get());
        }
        return null;
    }

    @CrossOrigin
    @GetMapping(value = "/users/email_validity", params = "email", produces = { MediaType.APPLICATION_JSON_VALUE })
    public boolean isUnique(@RequestParam String email) {
        return (personRepository.findByEmail(email).isPresent());
    }
}
