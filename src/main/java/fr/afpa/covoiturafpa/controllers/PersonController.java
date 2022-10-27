package fr.afpa.covoiturafpa.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;

import fr.afpa.covoiturafpa.model.Car;
import fr.afpa.covoiturafpa.model.Employee;
import fr.afpa.covoiturafpa.model.Formation;
import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.RidePassenger;
import fr.afpa.covoiturafpa.model.utils.NotifContentBuilder;
import fr.afpa.covoiturafpa.model.utils.PersonChecker;
import fr.afpa.covoiturafpa.model.utils.Views;
import fr.afpa.covoiturafpa.repository.CarRepository;
import fr.afpa.covoiturafpa.repository.EmployeeRepository;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.repository.RidePassengerRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.utils.captcha.HCaptchaService;
import fr.afpa.covoiturafpa.utils.captcha.PersonCreationRequest;
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
    private RidePassengerRepository ridePassengerRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ApplicationContext context;

    
    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
    @GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Person> list(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization) {
        try {
            String[] tokenArray = headerAuthorization.split(" ");
            CustomUsernamePasswordAuthenticationToken userAuthentication = JwtUtil.parseToken(tokenArray[1]);
            String roles = userAuthentication.getAuthorities().toString();
            if(roles.contains("ROLE_TEACHER") && roles.contains("ROLE_ADMIN")) {
                ArrayList<Person> result = new ArrayList<Person>();
                Iterable<Person> allTrainee =  personRepository.findAll();
                allTrainee.forEach(result::add);
                return result;
            }
            else if (roles.contains("ROLE_TEACHER")) {
                Employee teacher = employeeRepository.findById(userAuthentication.getIdUser()).get();
                List<Formation> formations = teacher.getTaughtFormations();
                ArrayList<Person> traineePerson = new ArrayList<Person>();
                for (Formation formation : formations) {
                    Iterator<Person> requestResult = personRepository.findByIdFormation(formation.getId()).iterator(); 
                    while (requestResult.hasNext()) {
                        traineePerson.add(requestResult.next());
                    }
                }
                return traineePerson;
            }
        }catch(Exception e) {
            Logger logger = LoggerFactory.getLogger(PersonController.class);
            logger.error("Erreur lors de la conception de la réponse JSon" + e);
        }
        return null;
    }
    
    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @PostMapping(value = "/users", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody PersonCreationRequest personCreationRequest) {
        HCaptchaService captchaService = new HCaptchaService(personCreationRequest.getCaptchaToken());
        Person newPerson = personCreationRequest.getNewPerson();
        if (captchaService.isValid() && PersonChecker.hasValidFields(newPerson)) {
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
    
    
    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @Transactional
    @PatchMapping(value = "/users/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization, @RequestBody Person updatedPerson) {
        try {
            String[] tokenArray = headerAuthorization.split(" ");
            CustomUsernamePasswordAuthenticationToken userAuthentication = JwtUtil.parseToken(tokenArray[1]);

            if (userAuthentication.getIdUser().equals(updatedPerson.getId())) {
                Optional<Person> optPerson = personRepository.findById(updatedPerson.getId());

                if (optPerson.isPresent()) {
                    Person person = optPerson.get();
                    
                    if (!person.getEmail().equals(updatedPerson.getEmail())) {
                        Pattern email = Pattern.compile("^[a-zA-Z0-9_+&*]+(?:[\\.\\-][a-zA-Z0-9_+&*]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,15}$");
                        Matcher mailMatcher = email.matcher(updatedPerson.getEmail());
                        
                        if (mailMatcher.matches() && !personRepository.findByEmail(updatedPerson.getEmail()).isPresent()) {
                            person.setEmail(updatedPerson.getEmail());
                        } else {
                            return ResponseEntity.badRequest().build();
                        }
                    }
                    
                    if (!person.getPhoneNumber().equals(updatedPerson.getPhoneNumber())) {
                        Pattern phoneNumber = Pattern.compile("^(\\+33|0|0033)[1-9]([. ]?[0-9]{2}){4}$");
                        Matcher phoneNumberMatcher = phoneNumber.matcher(updatedPerson.getPhoneNumber());
                        
                        if (phoneNumberMatcher.matches()) {
                            person.setPhoneNumber(updatedPerson.getPhoneNumber());
                        } else {
                            return ResponseEntity.badRequest().build();
                        }
                    }

                    if (updatedPerson.getPassword() != null) {
                        Pattern password = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$");
                        Matcher passwordMatcher = password.matcher(updatedPerson.getPassword());
                        
                        if (passwordMatcher.matches()) {
                            person.setPassword(context.getBean(PasswordEncoder.class).encode(updatedPerson.getPassword()));
                        } else {
                            return ResponseEntity.badRequest().build();
                        }
                    }
                    if (updatedPerson.isContactByMail() != person.isContactByMail()) {
                        person.setContactByMail(updatedPerson.isContactByMail());
                    }
                    if(updatedPerson.isContactBySms() != person.isContactBySms()) {
                        person.setContactBySms(updatedPerson.isContactBySms());
                    }
                    return ResponseEntity.ok(personRepository.save(person));
                }
            }
        } catch (JOSEException | ParseException | BadJOSEException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
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
    @PutMapping(value = "/users/{idDriver}/rides/{idRide}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, String>> manageReservation(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization, @PathVariable(required = true) int idRide, @RequestParam int idPassenger, @RequestParam boolean isAccepted) {
        HashMap<String, String> responseMessage = new HashMap<String, String>();
        try {
            String[] tokenArray = headerAuthorization.split(" ");
            CustomUsernamePasswordAuthenticationToken userAuthentication = JwtUtil.parseToken(tokenArray[1]);
            Ride ride = rideRepository.findById(idRide).get();
            if (userAuthentication.getIdUser().equals(ride.getDriver().getId())) {
                Person passenger = personRepository.findById(idPassenger).get();
                Ride updateRide = ride.manageBooking(passenger, isAccepted);
                rideRepository.save(updateRide);
                saveBookingNotification(ride, passenger, isAccepted);
                if(isAccepted) {
                    responseMessage.put("type", "success");
                    responseMessage.put("message", "Le passager a bien était accepté");
                    return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
                }else {
                    RidePassenger ridePassenger = ridePassengerRepository.findByPersonAndRide(passenger, ride).get();
                    ridePassengerRepository.delete(ridePassenger);
                    responseMessage.put("type", "success");
                    responseMessage.put("message", "Le passager a bien était refusé");
                    return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
                }
            }else {
                responseMessage.put("type", "error");
                responseMessage.put("message", "Vous n'êtes pas propriétaire de ce trajet");
                return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
            }
        }
        catch(Exception e) {
            responseMessage.put("type", "error");
            responseMessage.put("message", "Impossible de traité le JSON");
            return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
        }
        
        
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

    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    @CrossOrigin
    @PatchMapping(value = "/users/{id}/roles", consumes = "application/json-patch+json")
    public Person giveAdminOrTeacherAccess(@RequestBody Map<String, Boolean> data) {
        for (String key : data.keySet()){
            if (key == "isAdmin") {

            }
        }
        return null;
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
    public boolean isNotTaken(@RequestParam String email, @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) Optional<String> headerAuthorization) {
        
        if (headerAuthorization.isPresent()) {

            String[] tokenArray = headerAuthorization.get().split(" ");
            try {
                CustomUsernamePasswordAuthenticationToken userAuthentication = JwtUtil.parseToken(tokenArray[1]);
                Optional<Person> user = personRepository.findById(userAuthentication.getIdUser());
                if (user.isPresent() && user.get().getEmail().equals(email)) {
                    return true;
                }
            } catch (JOSEException | ParseException | BadJOSEException e) {
                e.printStackTrace();
            }
        }
        
        return (!personRepository.findByEmail(email).isPresent());
    }

}
