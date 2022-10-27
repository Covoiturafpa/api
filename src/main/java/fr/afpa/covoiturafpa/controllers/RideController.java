package fr.afpa.covoiturafpa.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.afpa.covoiturafpa.model.City;
import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.OneTimeRide;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.RecurringRide;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.utils.NotifContentBuilder;
import fr.afpa.covoiturafpa.model.utils.Views;
import fr.afpa.covoiturafpa.repository.CityRepository;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.utils.security.CustomUsernamePasswordAuthenticationToken;
import fr.afpa.covoiturafpa.utils.security.JwtUtil;

@RestController
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CityRepository cityRepository;

    @JsonView(Views.SimpleRide.class)
    @CrossOrigin
    @GetMapping(value = "/rides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> searchRelevantRides(@RequestParam String searchParams) {
        ObjectMapper objectMapper = new ObjectMapper();
        Ride ride;
        try {
            ride = objectMapper.readValue(searchParams, Ride.class);
            if (ride instanceof RecurringRide) {
                return searchRelevantRidesForRecurring(ride);
            } else {
                return searchRelevantRidesForOneTime(ride);
            }
        } catch (JsonProcessingException e) {
            Logger logger = LoggerFactory.getLogger(RideController.class);
            logger.error("Erreur dans la recherche de trajet : le JSON n'est pas exploitable.");
        }
        return null;
    }

    public Iterable<Ride> searchRelevantRidesForRecurring(Ride ride) {
        return rideRepository
                .filterRecurringRidesByDays(rideRepository.findRecurringRides(ride.getDestination().getCity().getName(),
                        ride.getDestination().getLatitude(), ride.getDestination().getLongitude(),
                        ((RecurringRide) ride).getBeginning(), ((RecurringRide) ride).getEnding(),
                        ride.getDestination().getIsFromAfpa()), ((RecurringRide) ride).getDaysWeek());
    }

    public Iterable<Ride> searchRelevantRidesForOneTime(Ride ride) {
        List<Ride> results = rideRepository.findOneTimeRides(ride.getDestination().getCity().getName(),
                ride.getDestination().getLatitude(), ride.getDestination().getLongitude(),
                ((OneTimeRide) ride).getDepartureDay(), ride.getDestination().getIsFromAfpa());
        List<DayWeek> days = new ArrayList<DayWeek>();
        days.add(new DayWeek(((OneTimeRide) ride).getDepartureDay().getDayOfWeek()));
        results.addAll(rideRepository.filterRecurringRidesByDays(rideRepository.findRecurringRides(
                ride.getDestination().getCity().getName(), ride.getDestination().getLatitude(),
                ride.getDestination().getLongitude(), ((OneTimeRide) ride).getDepartureDay(),
                ((OneTimeRide) ride).getDepartureDay(), ride.getDestination().getIsFromAfpa()), days));
        return results;
    }

    @JsonView(Views.DetailedRide.class)
    @Transactional
    @CrossOrigin
    @PostMapping(value = "/rides", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Ride create(@RequestBody Ride ride) {
        List<Ride> existingRide;
        try {
            if (ride instanceof RecurringRide) {
                RecurringRide recurringRide = (RecurringRide) ride;
                existingRide = rideRepository.findRecurringRidesByDateTimeAndDestination(
                        recurringRide.getDestination().getCity().getName(), recurringRide.getBeginning(),
                        recurringRide.getEnding(), recurringRide.getDestination().getIsFromAfpa());
            } else {
                OneTimeRide oneTimeRide = (OneTimeRide) ride;
                existingRide = rideRepository.findOneTimeRidesByDateTimeAndDestination(oneTimeRide.getDestination().getCity().getName(), oneTimeRide.getDepartureDay(), oneTimeRide.getDestination().getIsFromAfpa());
            }
            if (existingRide.size() == 0) {
                // Début de la création d'un ride
                Optional<Person> driver = personRepository.findById(ride.getCar().getPerson().getId());
                ride.setDriver(driver.get());
                Optional<City> city = cityRepository.findByName(ride.getDestination().getCity().getName());
                if (city.isPresent()) {
                    ride.getDestination().setCity(city.get());
                }
                if (ride instanceof RecurringRide) {
                    RecurringRide recurringRide = (RecurringRide) ride;
                    List<DayWeek> daysList = new ArrayList<DayWeek>();
                    for (DayWeek day : recurringRide.getDaysWeek()) {
                        Optional<DayWeek> dataDay = rideRepository.findByDay(day.getIdDayWeek());
                        daysList.add(dataDay.get());
                    }
                    recurringRide.setDaysWeek(daysList);
                    RecurringRide recurRide = rideRepository.save(recurringRide);
                    return recurRide;
                }
                return rideRepository.save(ride);
            }
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(RideController.class);
            logger.error("Erreur : Une erreur est survenu lors de la création");
        }
        return null;
    }

    @CrossOrigin
    @PutMapping(value = "/rides/{idRide}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, String>> book(@PathVariable(required = true) int idRide, @RequestParam int idPassenger) throws Exception {
        Ride ride = rideRepository.findById(idRide).get();
        Person passenger = personRepository.findById(idPassenger).get();
        HashMap<String, String> responseMessage = new HashMap<String, String>();
        if (!ride.hasBooking(passenger) && ride.addBooking(passenger)) {
            rideRepository.save(ride);
            Notification newNotification = new Notification(Notification.TypeNotif.NEW_RESERVATION,
                NotifContentBuilder.createNewBookingContent(passenger, ride), ride.getDriver());
            notificationRepository.save(newNotification);
            responseMessage.put("type", "success");
            responseMessage.put("message", "Demande de réservation effectuée");
            return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
        } else {
            responseMessage.put("type", "error");
            responseMessage.put("message", "Réservation impossible, utilisateur déjà inscrit");
            return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @PutMapping(value = "/update/rides/{idRide}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, String>> updateRide(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization, @PathVariable(required = true) int idRide, @RequestBody Ride ride) {
        HashMap<String, String> responseMessage = new HashMap<String, String>();
        try {
            String[] tokenArray = headerAuthorization.split(" ");
            CustomUsernamePasswordAuthenticationToken userAuthentication = JwtUtil.parseToken(tokenArray[1]);
            Optional<Ride> optionalRide = rideRepository.findById(idRide);
            if (optionalRide.isPresent()) {
                Integer idUser = optionalRide.get().getDriver().getId();
                if (userAuthentication.getIdUser().equals(idUser)) {
                    Ride newRide = optionalRide.get();
                    if (newRide.getComment().equals(ride.getComment())) {
                        newRide.setComment(ride.getComment());
                    }
                    if(newRide.getIsActive() == (ride.getIsActive())) {
                        newRide.setIsActive(ride.getIsActive());
                    }

                    rideRepository.save(newRide);
                    responseMessage.put("type", "success");
                    responseMessage.put("message", "Le trajet est modifié");
                    return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
                }else {
                    responseMessage.put("type", "error");
                    responseMessage.put("message", "Vous n'êtes pas propriétaire de ce trajet");
                    return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
                }
            }else {
                responseMessage.put("type", "error");
                responseMessage.put("message", "Il y a un problème sur ce trajet");
                return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
            }

        }catch(Exception e) {
            responseMessage.put("type", "error");
            responseMessage.put("message", "Impossible de traité le JSON");
            return new ResponseEntity<HashMap<String, String>>(responseMessage, HttpStatus.CREATED);
        }
    }

    @JsonView(Views.SimpleRide.class)
    @CrossOrigin
    @GetMapping(value = "/rides/{idRide}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Ride> get( @PathVariable(required = true) int idRide) {
        return rideRepository.findById(idRide);
    }

    @JsonView(Views.SimpleRide.class)
    @CrossOrigin
    @DeleteMapping(value = "/rides/{idRide}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuthorization, @PathVariable(required = true) Integer idRide) {
        try {
            String[] tokenArray = headerAuthorization.split(" ");
            CustomUsernamePasswordAuthenticationToken userAuthentication = JwtUtil.parseToken(tokenArray[1]);
            Optional<Ride> ride = rideRepository.findById(idRide);
            if (ride.isPresent()) {
                Integer idUser = ride.get().getDriver().getId();
                if (userAuthentication.getIdUser().equals(idUser)) {
                    rideRepository.deleteById(idRide);
                }
            }

        }catch(Exception e) {
            Logger logger = LoggerFactory.getLogger(PersonController.class);
            logger.error("Erreur lors de la conception de la réponse JSon" + e);
        }
        
    }
    
}
