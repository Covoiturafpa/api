package fr.afpa.covoiturafpa.utils.captcha;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HCaptchaService {
    private HCaptchaToken captchaToken;
    private final String secret = "0x6558B54dd6fb725f0bf6b418822a2aE3FE44C312";
    private final URI hCaptchaURI = URI.create("https://hcaptcha.com/siteverify");
    
    public HCaptchaToken getCaptchaToken() {
        return captchaToken;
    }
    public void setCaptchaToken(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    public String getSecret() {
        return secret;
    }

    public URI getHCaptchaURI() {
        return hCaptchaURI;
    }

    public HCaptchaService(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    public boolean isValid() {
        try {
        String body = "response=" + this.captchaToken.getToken() + "&secret=" + this.secret;
        HttpRequest request = HttpRequest.newBuilder()
            .uri(hCaptchaURI)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .timeout(Duration.ofSeconds(10))
            .POST(BodyPublishers.ofString(body))
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.body());
        return node.path("success").asBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}