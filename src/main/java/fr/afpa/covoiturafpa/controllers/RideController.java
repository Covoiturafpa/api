package fr.afpa.covoiturafpa.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.OneTimeRide;
import fr.afpa.covoiturafpa.model.RecurringRide;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.utils.Views;

@RestController
public class RideController {

    @Autowired
    private RideRepository rideRepository;
    
    @JsonView(Views.SimpleRide.class)
    @CrossOrigin
    @GetMapping(value = "/rides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> searchRelevantRides(@RequestBody Ride ride) {
        if (ride instanceof RecurringRide) {
            return searchRelevantRidesForRecurring(ride);
        }
        else {
            return searchRelevantRidesForOneTime(ride);
        }
    }

    public Iterable<Ride> searchRelevantRidesForRecurring(Ride ride) {
        return rideRepository.filterRecurringRidesByDays(rideRepository.findRecurringRides(ride.getDestination().getCity().getName(), ride.getDestination().getLatitude(), ride.getDestination().getLongitude(), ((RecurringRide) ride).getBeginning(), ((RecurringRide) ride).getEnding(), ride.getDestination().getIsFromAfpa()), ((RecurringRide) ride).getDaysWeek());
    }

    public Iterable<Ride> searchRelevantRidesForOneTime(Ride ride) {
        List<Ride> results = rideRepository.findOneTimeRides(ride.getDestination().getCity().getName(), ride.getDestination().getLatitude(), ride.getDestination().getLongitude(), ((OneTimeRide) ride).getDepartureDay(), ride.getDestination().getIsFromAfpa());
        Set<DayWeek> days = new HashSet<DayWeek>();
        days.add(new DayWeek(((OneTimeRide) ride).getDepartureDay().getDayOfWeek()));
        results.addAll(rideRepository.filterRecurringRidesByDays(rideRepository.findRecurringRides(ride.getDestination().getCity().getName(), ride.getDestination().getLatitude(), ride.getDestination().getLongitude(), ((OneTimeRide) ride).getDepartureDay(), ((OneTimeRide) ride).getDepartureDay(), ride.getDestination().getIsFromAfpa()), days));
        return results;
    }

   
    @CrossOrigin
    @PostMapping(value = "/rides", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Ride create(@RequestBody Ride ride) {
        return rideRepository.save(ride);
    }

    @CrossOrigin
    @PostMapping(value = "/rides/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Ride book(@PathVariable(required = true) int id, @RequestBody String jsonString) {
        return null;
    }
}
