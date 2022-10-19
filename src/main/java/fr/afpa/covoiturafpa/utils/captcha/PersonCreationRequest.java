package fr.afpa.covoiturafpa.utils.captcha;

import fr.afpa.covoiturafpa.model.Person;

public class PersonCreationRequest {
    private Person person;
    private HCaptchaToken captchaToken;
    
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
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
