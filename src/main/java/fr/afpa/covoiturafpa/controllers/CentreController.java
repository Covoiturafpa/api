package fr.afpa.covoiturafpa.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.covoiturafpa.model.Centre;
import fr.afpa.covoiturafpa.model.Partner;
import fr.afpa.covoiturafpa.repository.CentreRepository;
import fr.afpa.covoiturafpa.repository.PartnerRepository;

@RestController
public class CentreController {

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @CrossOrigin
    @GetMapping(value = "/centre", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Centre get() {
        if (centreRepository.countAll() == 1) {
            Iterable<Centre> result = centreRepository.findAll();
            return result.iterator().next();
        }
        else {
            Logger logger = LoggerFactory.getLogger(CentreController.class);
            logger.warn("Attention, plusieurs entrees dans la table Centre detectees");
        }
        return null;
    }
    
    @CrossOrigin
    @PostMapping(value = "/centre", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Centre update(@RequestBody(required = true) int id, @RequestBody String jsonString) {
        return null;
    }

    @CrossOrigin
    @GetMapping(value = "/centre/partners", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Partner> getPartner() {
        return partnerRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/centre/partners", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Partner createPartner(@RequestBody(required = true) Partner partner) {
        return partnerRepository.save(partner);
    }
        
    @CrossOrigin
    @DeleteMapping(value = "/centre/partners/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) int id) {
        partnerRepository.deleteById(id);;
    }
}
