package fr.afpa.covoiturafpa.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackerrank.test.utility.Order;

import fr.afpa.covoiturafpa.Application;
import fr.afpa.covoiturafpa.controllers.RideController;
import fr.afpa.covoiturafpa.model.City;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.repository.CentreRepository;
import fr.afpa.covoiturafpa.repository.CityRepository;
import fr.afpa.covoiturafpa.repository.FormationRepository;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class RidesTest {

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    @Order(1)
    public void should_create_new_ride() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode createdRideJSON = mapper.createObjectNode();
        ObjectNode destination = mapper.createObjectNode();
        ObjectNode cityNode = mapper.createObjectNode();
        ObjectNode car = mapper.createObjectNode();
        ObjectNode person = mapper.createObjectNode();
        destination.put("latitude", 45.7460663);
        destination.put("longitude", -0.6300671);
        destination.put("isFromAfpa", false);
        cityNode.put("name", "Saintes");
        destination.set("city", cityNode);
        car.put("id", 2);
        person.put("id", 51);
        person.put("personType", "E");
        car.set("person", person);
        createdRideJSON.put("rideType", "O");
        createdRideJSON.set("destination", destination);
        createdRideJSON.put("departureTime", "10:29:54");
        createdRideJSON.set("car", car);
        createdRideJSON.put("price", 0.5);
        createdRideJSON.put("comment", "ok");
        createdRideJSON.put("isActive", true);
        createdRideJSON.put("departureDay", "2022-10-27");

        RideController rideController = new RideController();

        try {
            Ride ride = mapper.readValue(createdRideJSON.toString(), Ride.class);
            Optional<Person> driver = personRepository.findById(ride.getCar().getPerson().getId());
            ride.setDriver(driver.get());
            Optional<City> city = cityRepository.findByName(ride.getDestination().getCity().getName());
            if (city.isPresent()) {
                ride.getDestination().setCity(city.get());
            }
            Ride newRide = rideRepository.save(ride);
            assertNotNull(newRide);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
