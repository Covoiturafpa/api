package fr.afpa.covoiturafpa.controllers;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.covoiturafpa.model.Centre;
import fr.afpa.covoiturafpa.model.Formation;
import fr.afpa.covoiturafpa.model.Partner;
import fr.afpa.covoiturafpa.repository.CentreRepository;
import fr.afpa.covoiturafpa.repository.FormationRepository;
import fr.afpa.covoiturafpa.repository.PartnerRepository;

@RestController
public class CentreController {

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private FormationRepository formationRepository;

    @PostConstruct
    public void verifyDatabase() throws Error {
        if (centreRepository.countAll() != 1) {
            Logger logger = LoggerFactory.getLogger(CentreController.class);
            logger.error("Attention, plusieurs entrees dans la table Centre detectees");
            throw new Error("Database contains invalid entries count in the table 'Centre' (max = 1)");
        }
    }

    @CrossOrigin
    @GetMapping(value = "/centre", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Centre get() {
        Iterable<Centre> result = centreRepository.findAll();
        return result.iterator().next();
    }
    
    @Secured("ROLE_ADMIN")
    @CrossOrigin
    @PutMapping(value = "/centre", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Centre update(@RequestBody Centre centre) {
        return centreRepository.save(centre);
    }

    @CrossOrigin
    @GetMapping(value = "/centre/partners", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Partner> getPartner() {
        return partnerRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/centre/partners", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Partner createPartner(@RequestBody(required = true) Partner partner) {
        partner.setCentre(get());
        return partnerRepository.save(partner);
    }
    
    @CrossOrigin
    @PutMapping(value = "/centre/partners/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Partner updatePartner(@PathVariable(required = true) int id, @RequestBody(required = true) Partner partner) {
        return partnerRepository.save(partner);
    }

    @CrossOrigin
    @DeleteMapping(value = "/centre/partners/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePartner(@PathVariable(required = true) int id) {
        partnerRepository.deleteById(id);
    }

    @CrossOrigin
    @GetMapping(value = "/centre/formations", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Formation> getFormation() {
        return formationRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/centre/formations", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Formation createFormation(@RequestBody(required = true) Formation formation) {
        formation.setCentre(get());
        return formationRepository.save(formation);
    }
    
    @CrossOrigin
    @PutMapping(value = "/centre/formations/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Formation updateFormation(@PathVariable(required = true) int id, @RequestBody(required = true) Formation formation) {
        return formationRepository.save(formation);
    }

    @CrossOrigin
    @DeleteMapping(value = "/centre/formations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFormation(@PathVariable(required = true) int id) {
        formationRepository.deleteById(id);
    }
}
