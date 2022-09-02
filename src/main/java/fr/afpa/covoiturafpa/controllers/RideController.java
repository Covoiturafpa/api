package fr.afpa.covoiturafpa.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.covoiturafpa.model.Ride;

@RestController
public class RideController {
    
    @CrossOrigin
    @GetMapping(value = "/rides", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ride> list() {
        return null;
    }

    @CrossOrigin
    @PostMapping(value = "/rides", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Ride create(@RequestBody String jsonString) {
        return null;
    }

    @CrossOrigin
    @PostMapping(value = "/rides/{id}", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Ride book(@PathVariable(required = true) int id, @RequestBody String jsonString) {
        return null;
    }
}
