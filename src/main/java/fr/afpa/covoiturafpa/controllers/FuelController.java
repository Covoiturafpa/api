package fr.afpa.covoiturafpa.controllers;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.afpa.covoiturafpa.model.Fuel;
import fr.afpa.covoiturafpa.repository.FuelRepository;

@RestController
@RequestMapping("/api/fuels")
public class FuelController {
    
    @Autowired
    private FuelRepository fuelRepository;

    private Logger logger = LoggerFactory.getLogger(FuelController.class);

    @CrossOrigin
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Iterable<Fuel>> list() {
        Iterable<Fuel> fuels = fuelRepository.findAll();

        if (StreamSupport.stream(fuels.spliterator(), false).count() > 0) {
            return ResponseEntity.ok(fuels);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Fuel> get(@PathVariable(required = true) int id) {
        Optional<Fuel> foundFuel = null;
        try {
            foundFuel = fuelRepository.findById(id);
        } catch (IllegalArgumentException exception) {
            logger.error("Le paramètre passé au endpoint n'est pas correct.");
            return ResponseEntity.badRequest().build();
        }

        if (foundFuel.isPresent()) {
            return ResponseEntity.ok(foundFuel.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @CrossOrigin
    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Fuel> updatePrice(@PathVariable(required = true) int id, @RequestBody Fuel fuel) {
        try {
            fuelRepository.save(fuel);
        } catch (IllegalArgumentException exception) {
            logger.error("Le paramètre passé au endpoint n'est pas correct.");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
