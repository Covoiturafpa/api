package fr.afpa.covoiturafpa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.afpa.covoiturafpa.model.Fuel;
import fr.afpa.covoiturafpa.repository.FuelRepository;

@RestController
public class FuelController {
    
    @Autowired
    private FuelRepository fuelRepository;
    
    @CrossOrigin
    @GetMapping(value = "/fuels", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Fuel> list() {
        return fuelRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/fuels/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Optional<Fuel> get(@PathVariable(required = true) int id) {
        return fuelRepository.findById(id);
    }
    
    @CrossOrigin
    @PostMapping(value = "/fuels/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Fuel updatePrice(@PathVariable(required = true) int id, @RequestBody String jsonString) {
        return fuelRepository.save(new Fuel());
    }
}
