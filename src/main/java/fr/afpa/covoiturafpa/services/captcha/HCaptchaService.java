package fr.afpa.covoiturafpa.services.captcha;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class HCaptchaService {

    private HCaptchaToken captchaToken;

    private final HCaptchaConfig hCaptchaConfig;

    public HCaptchaService(HCaptchaConfig hCaptchaConfig) {
        this.hCaptchaConfig = hCaptchaConfig;
    }

    public HCaptchaToken getCaptchaToken() {
        return captchaToken;
    }

    public void setCaptchaToken(HCaptchaToken captchaToken) {
        this.captchaToken = captchaToken;
    }

    public boolean isValid() {
        JsonNode responseJson = this.getValidityResponseAsJson();
        return responseJson.path("success").asBoolean();
    }

    public JsonNode getValidityResponseAsJson() {
        try {
            String body = "response=" + this.captchaToken.getToken() + "&secret=" + this.hCaptchaConfig.getSecret();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(this.hCaptchaConfig.getUri()))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .timeout(Duration.ofSeconds(10))
                    .POST(BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            ObjectNode successJson = JsonNodeFactory.instance.objectNode();
            successJson.put("success", false);
            return successJson;
        }
    }

}
