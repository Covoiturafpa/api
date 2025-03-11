package fr.afpa.covoiturafpa.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.dto.LoginResponseDto;
import fr.afpa.covoiturafpa.dto.LoginUserDto;
import fr.afpa.covoiturafpa.dto.PersonCreationRequestDto;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.utils.Views;
import fr.afpa.covoiturafpa.services.PersonService;
import fr.afpa.covoiturafpa.services.authentication.AuthenticationService;
import fr.afpa.covoiturafpa.services.authentication.JwtService;
import fr.afpa.covoiturafpa.services.captcha.HCaptchaService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final HCaptchaService hCaptchaService;
    private final PersonService personService;

    /**
     * Injection par paramètre de constructeur des 2 beans : jwtService et
     * authenticationService
     * 
     * @param jwtService            Gère les opérations sur les JWT
     * @param authenticationService Gère les opérations
     */
    public AuthenticationController(JwtService jwtService,
                                    AuthenticationService authenticationService,
                                    HCaptchaService hCaptchaService,
                                    PersonService personService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.hCaptchaService = hCaptchaService;
        this.personService = personService;
    }

    /**
     * Méthode traitant une requête POST de création d'utilisateur.
     * 
     * @param personCreationRequest
     * @return
     */
    @JsonView(Views.DetailedUser.class)
    @CrossOrigin
    @PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> register(@RequestBody PersonCreationRequestDto personCreationRequest) {

        // configuration du "hCaptchaService" avec le token du captcha (pas le JWT, c'est pas de ma faute si tout s'appelle token aussi...)
        hCaptchaService.setCaptchaToken(personCreationRequest.getCaptchaToken());
        Person newPerson = personCreationRequest.getPerson();
        
        if (hCaptchaService.isValid() && personService.isValidNewPerson(newPerson)) {
            return ResponseEntity.ok(personService.createPerson(newPerson));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "not created"));
    }

    /**
     * Traite les requête de connexion d'un utilisateur
     * 
     * @param userDto DTO permettant de récupérer les informations de connexion
     * @return La réponse de connexion
     */
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto userDto) {

        // tentative de connexion via le service approprié
        Person authenticatedUser = authenticationService.login(userDto);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", authenticatedUser.getId());
     
        // création du JWT
        String jwtToken = jwtService.generateToken(extraClaims, authenticatedUser);

        // création de la réponse client
        LoginResponseDto loginResponse = new LoginResponseDto().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}