package fr.afpa.covoiturafpa.utils.hcaptcha;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

import com.nimbusds.jose.shaded.gson.JsonObject;

public class HCaptchaService {
    private HCaptchaToken captchaToken;
    private final String secret = "0x6558B54dd6fb725f0bf6b418822a2aE3FE44C312";

    public HCaptchaToken getCaptchaToken() {
        return captchaToken;
    }
    public void setCaptchaToken(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }
    public String getSecret() {
        return secret;
    }

    public HCaptchaService(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    // public boolean isValid() {
    //     return this.getValidity().getSuccess();
    // }

    // public HCaptchaResponse getValidity() {
    //     String body = "response=" + this.captchaToken.getToken() + "&secret=" + this.secret;
    //     HttpRequest request = HttpRequest.newBuilder()
    //         .uri(URI.create("https://hcaptcha.com/siteverify"))
    //         .header("Content-Type", "application/x-www-form-urlencoded")
    //         .timeout(Duration.ofSeconds(10))
    //         .POST(BodyPublishers.ofString(body))
    //         .build();
    //     HttpResponse<HCaptchaResponse> response = HttpClient.newHttpClient().send(request, null)
    // }
}