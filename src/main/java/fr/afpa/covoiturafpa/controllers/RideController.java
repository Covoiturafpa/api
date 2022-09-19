package fr.afpa.covoiturafpa.controllers;

import org.springframework.http.MediaType;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.covoiturafpa.model.RecurringRide;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.repository.RideRepository;
import fr.afpa.covoiturafpa.model.DayWeek;
import fr.afpa.covoiturafpa.model.OneTimeRide;;

@RestController
public class RideController {

    @Autowired
    private RideRepository rideRepository;
    
    @CrossOrigin
    @GetMapping(value = "/rides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> searchRelevantRides(@RequestBody Ride ride) {
        if (ride instanceof RecurringRide) {
            return rideRepository.findRecurringRides(ride.getDestination(), ((RecurringRide) ride).getBeginning(), ((RecurringRide) ride).getEnding(), ((RecurringRide) ride).getDaysWeek() );
        }
        else {
            Set<DayWeek> day = new HashSet<DayWeek>();
            day.add(new DayWeek(((OneTimeRide) ride).getDepartureDay().getDayOfWeek()));
            
            return rideRepository.findOneTimeRides(ride.getDestination(), ((OneTimeRide) ride).getDepartureDay()).addAll(rideRepository.findRecurringRides(ride.getDestination(), ((OneTimeRide) ride).getDepartureDay(), ((OneTimeRide) ride).getDepartureDay(), day));
        }
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
