package fr.afpa.covoiturafpa.utils.hcaptcha;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HCaptchaResponse {
    private boolean success;
    private LocalDateTime challenge_ts;
    private String hostname;
    private ArrayList<String> errorCodes;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LocalDateTime getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(LocalDateTime challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public ArrayList<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(ArrayList<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public HCaptchaResponse() {
    }    
}
