package fr.afpa.covoiturafpa.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.Car;
import fr.afpa.covoiturafpa.model.Employee;
import fr.afpa.covoiturafpa.model.Formation;
import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.RidePassenger;
import fr.afpa.covoiturafpa.model.utils.NotifContentBuilder;
import fr.afpa.covoiturafpa.model.utils.Views;
import fr.afpa.covoiturafpa.repository.CarRepository;
import fr.afpa.covoiturafpa.repository.EmployeeRepository;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.repository.RidePassengerRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.services.PersonService;
import fr.afpa.covoiturafpa.services.authentication.JwtService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/users")
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

    @Autowired
    private PersonService personService;

    // TODO ajouter constructeur pour injection de dépendances

    /**
     * Injection du service de traitement des JWT
     * TODO : déplacer plus de la logique métier dans un service "PersonService"
     */
    @Autowired
    private JwtService jwtService;

    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @Secured({ "ROLE_TEACHER", "ROLE_ADMIN" })
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Person> list(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization) {
        // permet de récupérer la liste des utilisateurs si et seulement si
        // l'utilisateur a les rôles suivants :
        // ROLE_TEACHER ou ROLE_ADMIN
        try {
            // récupération des différentes parties du token
            // tokenArray[0] correspond à la chaîne "bearer"
            // tokenArray[1] correspond au JWT
            String[] tokenArray = headerAuthorization.split(" ");
            // extraction du token
            String email = jwtService.extractUsername(tokenArray[1]);

            // le nom est, normalement unique, on s'en sort pour retrouver l'utilisateur en
            // BDD
            Optional<Person> optionalPerson = personRepository.findByEmail(email);

            if (optionalPerson.isPresent()) {
                // Cas à traiter :
                // 1. les rôles de l'utilisateur qui a fait la demande sont "TEACHER" && "ADMIN"
                // -> on renvoie tout le monde
                // 2. le rôle est uniquement "TEACHER" -> on renvoie que les stagiaires des
                // formations qu'il mène

                // récupération de la personne à partir de l'optional
                Person person = optionalPerson.get();
                List<String> authorities = person.getStringAuthorities();

                // "TEACHER" ET "ADMIN"
                if (authorities.contains("ROLE_TEACHER") && authorities.contains("ROLE_ADMIN")) {
                    ArrayList<Person> result = new ArrayList<Person>();
                    Iterable<Person> allTrainee = personRepository.findAll();
                    allTrainee.forEach(result::add);
                    return result;
                } else if (authorities.contains("ROLE_TEACHER")) {
                    Employee teacher = employeeRepository.findById(person.getId()).get();

                    List<Formation> formations = teacher.getTaughtFormations();
                    ArrayList<Person> traineePerson = new ArrayList<Person>();
                    for (Formation formation : formations) {
                        Iterator<Person> requestResult = personRepository.findByIdFormation(formation.getId())
                                .iterator();
                        while (requestResult.hasNext()) {
                            traineePerson.add(requestResult.next());
                        }
                    }
                    return traineePerson;
                }
            } else { // pas d'utilisateur retrouvé en BDD
                Logger logger = LoggerFactory.getLogger(PersonController.class);
                logger.error("Utilisateur du token non existant");
                return null;
            }
            // CustomUsernamePasswordAuthenticationToken userAuthentication =
            // JwtUtil.parseToken(jwtService);

        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(PersonController.class);
            logger.error("Erreur lors de la conception de la réponse JSon" + e);
        }
        return null;
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin
    @DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInactivePersonsForSixMonths() {
        // TODO: personRepository.deleteInactiveForSixMonths();
        // corriger la requête SQL (à tester avec des tests unitaires)
    }

    @CrossOrigin
    @GetMapping(value = "/username/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> getByEmail(@PathVariable(required = true) String username) {
        return personRepository.findByEmail(username);
    }

    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> get(@PathVariable(required = true) Integer id) {

        // TODO que se passe-t-il si pas d'utilisateur ?
        // peut être ResponseEntity à renvoyer
        return personRepository.findById(id);
    }

    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @Transactional
    @PatchMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization,
            @RequestBody Person updatedPerson) {
        // TODO: passer par les méthodes de PersonInfoChecker pour les REGEX

        String[] tokenArray = headerAuthorization.split(" ");

        Integer userId = jwtService.extractId(tokenArray[1]);
        Optional<Person> optionalPerson = personService.findPersonById(userId);

        if (optionalPerson.isPresent()) {
            // vérification : s'agit-il bien de l'utilisateur qui & envoyé la requête ?
            if (optionalPerson.get().getId().equals(updatedPerson.getId())) {
                // TODO optimiser, est-il pertinent de refaire un find ?
                Optional<Person> optPerson = personRepository.findById(updatedPerson.getId());

                if (optPerson.isPresent()) {
                    Person person = optPerson.get();

                    // TODO: passer par les méthodes de PersonInfoChecker pour les REGEX
                    if (!person.getEmail().equals(updatedPerson.getEmail())) {
                        Pattern email = Pattern.compile(
                                "^[a-zA-Z0-9_+&*]+(?:[\\.\\-][a-zA-Z0-9_+&*]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,15}$");
                        Matcher mailMatcher = email.matcher(updatedPerson.getEmail());

                        if (mailMatcher.matches()
                                && !personRepository.findByEmail(updatedPerson.getEmail()).isPresent()) {
                            person.setEmail(updatedPerson.getEmail());
                        } else {
                            return ResponseEntity.badRequest().build();
                        }
                    }

                    // TODO: passer par les méthodes de PersonInfoChecker pour les REGEX
                    if (!person.getPhoneNumber().equals(updatedPerson.getPhoneNumber())) {
                        Pattern phoneNumber = Pattern.compile("^(\\+33|0|0033)[1-9]([. ]?[0-9]{2}){4}$");
                        Matcher phoneNumberMatcher = phoneNumber.matcher(updatedPerson.getPhoneNumber());

                        if (phoneNumberMatcher.matches()) {
                            person.setPhoneNumber(updatedPerson.getPhoneNumber());
                        } else {
                            return ResponseEntity.badRequest().build();
                        }
                    }

                    // TODO: passer par les méthodes de PersonInfoChecker pour les REGEX
                    if (updatedPerson.getPassword() != null) {
                        Pattern password = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$");
                        Matcher passwordMatcher = password.matcher(updatedPerson.getPassword());

                        if (passwordMatcher.matches()) {
                            person.setPassword(
                                    context.getBean(PasswordEncoder.class).encode(updatedPerson.getPassword()));
                        } else {
                            return ResponseEntity.badRequest().build();
                        }
                    }
                    if (updatedPerson.isContactByMail() != person.isContactByMail()) {
                        person.setContactByMail(updatedPerson.isContactByMail());
                    }
                    if (updatedPerson.isContactBySms() != person.isContactBySms()) {
                        person.setContactBySms(updatedPerson.isContactBySms());
                    }
                    return ResponseEntity.ok(personRepository.save(person));
                }
            }
        } else {
            // sécurisaiton : VOUS NE PASSEREZ PAS (non mais oh.)
            // TODO faire mieux qu'un simple "badRequest", traitement des erreurs ?
            // Qu'est-ce qu'on renvoie au client ?
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.badRequest().build();
    }

    // TODO peut être renvoyer quelque chose ?
    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) int id) {

        Optional<Person> optionalPerson = personService.findPersonById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            personRepository.delete(person);
        }
    }

    /**
     * Retourne les trajets d'une personne
     * 
     * @param headerAuthorization
     * @param id L'identifiant de la personne concerné
     * @return Liste de trajet
     */
    @JsonView(Views.DetailedRide.class)
    @CrossOrigin
    @GetMapping(value = "/{id}/rides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> getRidesOfPerson(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization,
            @PathVariable(required = true) Integer id) {
        try {
            // TODO mettre cette logique de traitement du token ailleurs
            String[] tokenArray = headerAuthorization.split(" ");
            Integer idUserRequest = jwtService.extractId(tokenArray[1]);

            // vérification de l'utilisateur a l'origine de la requête
            if (idUserRequest == id) {
                return rideRepository.findRidesByPerson(id);
            }
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(PersonController.class);
            logger.error("Erreur lors de la conception de la réponse JSon" + e);
        }
        return null;
    }

    @CrossOrigin
    @PutMapping(value = "/{idDriver}/rides/{idRide}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, String>> manageReservation(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization,
            @PathVariable(required = true) int idRide, @RequestParam int idPassenger,
            @RequestParam boolean isAccepted) {
        HashMap<String, String> responseMessage = new HashMap<String, String>();
        try {
            // TODO mettre la logique du "split" et récupération du token dans une fonciton (vu que c'est utilisé absolument partout)
            String[] tokenArray = headerAuthorization.split(" ");
            Integer idUserRequest = jwtService.extractId(tokenArray[1]);

            Ride ride = rideRepository.findById(idRide).get();

            if (idUserRequest == ride.getDriver().getId()) {
                Person passenger = personRepository.findById(idPassenger).get();
                Ride updateRide = ride.manageBooking(passenger, isAccepted);
                rideRepository.save(updateRide);
                saveBookingNotification(ride, passenger, isAccepted);
                if (isAccepted) {
                    responseMessage.put("type", "success");
                    responseMessage.put("message", "Le passager a bien était accepté");
                    return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
                } else {
                    RidePassenger ridePassenger = ridePassengerRepository.findByPersonAndRide(passenger, ride).get();
                    ridePassengerRepository.delete(ridePassenger);
                    responseMessage.put("type", "success");
                    responseMessage.put("message", "Le passager a bien était refusé");
                    return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
                }
            } else {
                responseMessage.put("type", "error");
                responseMessage.put("message", "Vous n'êtes pas propriétaire de ce trajet");
                return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            responseMessage.put("type", "error");
            responseMessage.put("message", "Impossible de traité le JSON");
            return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
        }

    }

    public void saveBookingNotification(Ride ride, Person passenger, boolean isAccepted) {
        if (isAccepted) {
            Notification newNotification = new Notification(Notification.TypeNotif.ACCEPTED_RESERVATION,
                    NotifContentBuilder.createAcceptedBookingContent(ride), passenger);
            notificationRepository.save(newNotification);
        } else {
            Notification newNotification = new Notification(Notification.TypeNotif.REJECTED_RESERVATION,
                    NotifContentBuilder.createRejectedBookingContent(ride), passenger);
            notificationRepository.save(newNotification);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/{id}/cars", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Car createCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            car.setPerson(person.get());
            return carRepository.save(car);
        }
        return null;
    }

    @CrossOrigin
    @PatchMapping(value = "/{id}/cars", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Car updateCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        if (car.getPerson().getId() == id) {
            return carRepository.save(car);
        }
        return null;
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}/cars", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void deleteCar(@PathVariable(required = true) int id, @RequestBody Car car) {
        if (car.getPerson().getId() == id) {
            carRepository.delete(car);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/{id}/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Notification> getNotifications(@PathVariable(required = true) int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get().getNotifications();
        }
        return null;
    }

    @CrossOrigin
    @PutMapping(value = "/{id}/notifications", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public int setAsReadNotifications(@PathVariable(required = true) int id) {
        return notificationRepository.updateAllUnreadByPerson(id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}/notifications")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllNotification(@PathVariable(required = true) int id) {
        notificationRepository.deleteAllByPerson(personRepository.findById(id).get());
    }

    @CrossOrigin
    @DeleteMapping(value = "/{idUser}/notifications", params = "idNotification")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotificationById(@PathVariable(required = true) Integer idUser,
            @RequestParam Integer idNotification) {
        notificationRepository.deleteByIdAndPerson(idNotification, personRepository.findById(idUser).get());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}/new_notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public boolean checkNewNotifications(@PathVariable(required = true) int id) {
        return (notificationRepository.countNewNotifications(id) > 0);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @CrossOrigin
    @PatchMapping(value = "/{id}/roles", consumes = "application/json-patch+json")
    public Person giveAdminOrTeacherAccess(@RequestBody Map<String, Boolean> data) {
        for (String key : data.keySet()) {
            if (key == "isAdmin") {

            }
        }
        return null;
    }

    @Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @CrossOrigin
    @PatchMapping(value = "/{id}/activation", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Person activateAccount(@PathVariable(required = true) int id, @RequestBody Person user) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            person.get().setIsActivated(true);
            return personRepository.save(person.get());
        }
        return null;
    }

    @CrossOrigin
    @GetMapping(value = "/email_validity", params = "email", produces = { MediaType.APPLICATION_JSON_VALUE })
    public boolean isNotTaken(@RequestParam String email,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) Optional<String> headerAuthorization) {

        if (headerAuthorization.isPresent()) {

            String[] tokenArray = headerAuthorization.get().split(" ");
            Integer idUserRequest = jwtService.extractId(tokenArray[1]);

            Optional<Person> user = personService.findPersonById(idUserRequest);
            if (user.isPresent() && user.get().getEmail().equals(email)) {
                return true;
            }

        }

        return (!personRepository.findByEmail(email).isPresent());
    }

}
