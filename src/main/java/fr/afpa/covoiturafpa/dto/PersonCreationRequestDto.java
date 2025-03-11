package fr.afpa.covoiturafpa.dto;

import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.services.captcha.HCaptchaToken;

/**
 * Classe utilisée lors de la création d'un nouvel utilisateur
 */
public class PersonCreationRequestDto {

    private Person person;
    
    private HCaptchaToken captchaToken;
    
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person newPerson) {
        this.person = newPerson;
    }
    public HCaptchaToken getCaptchaToken() {
        return captchaToken;
    }
    public void setCaptchaToken(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    public PersonCreationRequestDto() {
    }
}
