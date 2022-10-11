package fr.afpa.covoiturafpa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.OneTimeRide;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.RecurringRide;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.utils.NotifContentBuilder;
import fr.afpa.covoiturafpa.model.utils.Views;
import fr.afpa.covoiturafpa.repository.NotificationRepository;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;

@RestController
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private NotificationRepository notificationRepository;

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
            }
            else {
                return searchRelevantRidesForOneTime(ride);
        }
        } catch (JsonProcessingException e) {
            Logger logger = LoggerFactory.getLogger(CentreController.class);
            logger.error("Erreur dans la recherche de trajet : le JSON n'est pas exploitable.");
        } 
        return null;
    }

    public Iterable<Ride> searchRelevantRidesForRecurring(Ride ride) {
        return rideRepository.filterRecurringRidesByDays(rideRepository.findRecurringRides(ride.getDestination().getCity().getName(), ride.getDestination().getLatitude(), ride.getDestination().getLongitude(), ((RecurringRide) ride).getBeginning(), ((RecurringRide) ride).getEnding(), ride.getDestination().getIsFromAfpa()), ((RecurringRide) ride).getDaysWeek());
    }

    public Iterable<Ride> searchRelevantRidesForOneTime(Ride ride) {
        List<Ride> results = rideRepository.findOneTimeRides(ride.getDestination().getCity().getName(), ride.getDestination().getLatitude(), ride.getDestination().getLongitude(), ((OneTimeRide) ride).getDepartureDay(), ride.getDestination().getIsFromAfpa());
        List<DayWeek> days = new ArrayList<DayWeek>();
        days.add(new DayWeek(((OneTimeRide) ride).getDepartureDay().getDayOfWeek()));
        results.addAll(rideRepository.filterRecurringRidesByDays(rideRepository.findRecurringRides(ride.getDestination().getCity().getName(), ride.getDestination().getLatitude(), ride.getDestination().getLongitude(), ((OneTimeRide) ride).getDepartureDay(), ((OneTimeRide) ride).getDepartureDay(), ride.getDestination().getIsFromAfpa()), days));
        return results;
    }

   
    @CrossOrigin
    @PostMapping(value = "/rides", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Ride create(@RequestBody Ride ride) {
        return rideRepository.save(ride);
    }

    @CrossOrigin
    @PutMapping(value = "/rides/{idRide}")
    @ResponseStatus(HttpStatus.OK)
    public void book(@PathVariable(required = true) int idRide, @RequestParam int idPassenger) {
        Ride ride = rideRepository.findById(idRide).get();
        Person passenger = personRepository.findById(idPassenger).get();
        if (ride.addBooking(passenger)) {
            rideRepository.save(ride);
            Notification newNotification = new Notification(Notification.TypeNotif.NEW_RESERVATION, NotifContentBuilder.createNewBookingContent(passenger, ride), ride.getDriver());
            notificationRepository.save(newNotification);
        }
    }
}
