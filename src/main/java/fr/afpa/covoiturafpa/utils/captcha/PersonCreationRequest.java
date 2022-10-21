package fr.afpa.covoiturafpa.utils.captcha;

import fr.afpa.covoiturafpa.model.Person;

public class PersonCreationRequest {
    private Person newPerson;
    private HCaptchaToken captchaToken;
    
    public Person getNewPerson() {
        return newPerson;
    }
    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }
    public HCaptchaToken getCaptchaToken() {
        return captchaToken;
    }
    public void setCaptchaToken(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    public PersonCreationRequest() {
    }
}
