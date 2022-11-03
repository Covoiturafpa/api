package fr.afpa.covoiturafpa.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackerrank.test.utility.Order;

import fr.afpa.covoiturafpa.Application;
import fr.afpa.covoiturafpa.model.City;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.repository.CityRepository;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.repository.RideRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class RidesTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RideController rideController;

    private ObjectMapper mapper = new ObjectMapper();

    private Ride createRide(String rideType, String cityName, Double latitude, Double longitude, Boolean isFromAfpa,
            int carId, int personId, String personType, String departureTime, float price, String comment,
            Boolean isActive, String departureDay, String beginning, String ending, List<Integer> days) {

        ObjectNode createdRideJSON = mapper.createObjectNode();
        ObjectNode destination = mapper.createObjectNode();
        ObjectNode cityNode = mapper.createObjectNode();
        ObjectNode car = mapper.createObjectNode();
        ObjectNode person = mapper.createObjectNode();
        destination.put("latitude", latitude);
        destination.put("longitude", longitude);
        destination.put("isFromAfpa", isFromAfpa);
        cityNode.put("name", cityName);
        destination.set("city", cityNode);
        car.put("id", carId);
        person.put("id", personId);
        person.put("personType", personType);
        car.set("person", person);
        createdRideJSON.put("rideType", rideType);
        createdRideJSON.set("destination", destination);
        createdRideJSON.put("departureTime", departureTime);
        createdRideJSON.set("car", car);
        createdRideJSON.put("price", price);
        createdRideJSON.put("comment", comment);
        createdRideJSON.put("isActive", isActive);
        if (rideType.equals("O")) {
            createdRideJSON.put("departureDay", departureDay);
        }
        if (rideType.equals("R")) {
            ArrayNode daysWeek = mapper.createArrayNode();
            for (Integer dayNumber : days) {
                ObjectNode dayNode = mapper.createObjectNode();
                dayNode.put("idDayWeek", dayNumber);
                daysWeek.add(dayNode);
            }
            createdRideJSON.set("daysWeek", daysWeek);
            createdRideJSON.put("beginning", beginning);
            createdRideJSON.put("ending", ending);
        }
        try {
            Ride ride = mapper.readValue(createdRideJSON.toString(), Ride.class);
            Optional<Person> driver = personRepository.findById(ride.getCar().getPerson().getId());
            ride.setDriver(driver.get());
            Optional<City> city = cityRepository.findByName(ride.getDestination().getCity().getName());
            if (city.isPresent()) {
                ride.getDestination().setCity(city.get());
            }
            Ride newRide = rideController.create(ride);
            return newRide;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    @Order(1)
    public void should_create_new_ride() {
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
            e.printStackTrace();
        }

    }

    @Test
    @Order(2)
    public void shouldnt_find_null_ride() {
        String wrongStringParameter = "{'id':'45454','rideType':'O','cityName':'Moon','Animals':true}";
        Iterable<Ride> rideResults = rideController.searchRelevantRides(wrongStringParameter);
        assertNull(rideResults);
    }

    @Test
    @Order(3)
    public void should_find_valid_one_time_ride() {
        Ride oneTime = createRide(
                "O",
                "Surgères",
                12.45,
                42.33,
                false,
                4,
                51,
                "E",
                "10:12:12",
                2.2f,
                "lorem",
                true,
                "2022-10-27",
                null,
                null,
                null);

        try {
            String json = mapper.writeValueAsString(oneTime);
            String encodedParameters = encodeValue(json);
            Iterable<Ride> rideResults = rideController.searchRelevantRides(encodedParameters);
            assertNotNull(rideResults);
            assertFalse(rideResults.iterator().hasNext());
            Ride foundRide = rideResults.iterator().next();
            assertEquals(oneTime, foundRide);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void shouldnt_find_inexistant_one_time_ride() {
        Ride oneTime = createRide(
                "O",
                "Surgères",
                12.45,
                42.33,
                false,
                4,
                51,
                "E",
                "10:12:12",
                2.2f,
                "lorem",
                true,
                "2022-10-27",
                null,
                null,
                null);

        try {
            String json = mapper.writeValueAsString(oneTime);
            String encodedParameters = encodeValue(json);
            rideRepository.delete(oneTime);
            Iterable<Ride> rideResults = rideController.searchRelevantRides(encodedParameters);
            assertNotNull(rideResults);
            assertFalse(rideResults.iterator().hasNext());
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    public void should_find_valid_recurring_ride() {
        List<Integer> daysWeek = List.of(4,5);
        
        Ride recurringRide = createRide(
                "R",
                "Surgères",
                12.45,
                42.33,
                false,
                4,
                51,
                "E",
                "10:12:12",
                2.2f,
                "lorem",
                true,
                null,
                "2022-10-27",
                "2022-10-31",
                daysWeek);

        try {
            String json = mapper.writeValueAsString(recurringRide);
            String encodedParameters = encodeValue(json);
            Iterable<Ride> rideResults = rideController.searchRelevantRides(encodedParameters);
            assertNotNull(rideResults);
            assertFalse(rideResults.iterator().hasNext());
            Ride foundRide = rideResults.iterator().next();
            assertEquals(recurringRide, foundRide);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    public void shouldnt_find_inexistant_recurring_ride() {
        List<Integer> daysWeek = List.of(4,5);
        
        Ride recurringRide = createRide(
                "R",
                "Surgères",
                12.45,
                42.33,
                false,
                4,
                51,
                "E",
                "10:12:12",
                2.2f,
                "lorem",
                true,
                null,
                "2022-10-27",
                "2022-10-31",
                daysWeek);
                
        try {
            String json = mapper.writeValueAsString(recurringRide);
            String encodedParameters = encodeValue(json);
            rideRepository.delete(recurringRide);
            Iterable<Ride> rideResults = rideController.searchRelevantRides(encodedParameters);
            assertNotNull(rideResults);
            assertFalse(rideResults.iterator().hasNext());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
