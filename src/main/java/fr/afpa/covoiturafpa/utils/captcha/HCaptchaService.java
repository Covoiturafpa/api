package fr.afpa.covoiturafpa.utils.captcha;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HCaptchaService {

    private HCaptchaToken captchaToken;

    @Value("${hcaptcha.secret}")
    private String secret;

    @Value("${hcaptcha.uri.string}")
    private String hCaptchaURIString;
    
    public HCaptchaToken getCaptchaToken() {
        return captchaToken;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setCaptchaToken(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    public String getHCaptchaURIString() {
        return hCaptchaURIString;
    }

    public HCaptchaService() {
    }
    
    public HCaptchaService(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    public boolean isValid() {
        JsonNode responseJson = this.getValidityResponseAsJson();
        return responseJson.path("success").asBoolean();
    }

    public JsonNode getValidityResponseAsJson() {
        try {
            String body = "response=" + this.captchaToken.getToken() + "&secret=" + this.secret;
            HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(hCaptchaURIString))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .timeout(Duration.ofSeconds(10))
                .POST(BodyPublishers.ofString(body))
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.body());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }
}