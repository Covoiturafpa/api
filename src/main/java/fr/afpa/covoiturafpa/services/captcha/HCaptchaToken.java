package fr.afpa.covoiturafpa.services.captcha;

public class HCaptchaToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HCaptchaToken() {
    }

    public HCaptchaToken(String token) {
        this.token = token;
    }
}
