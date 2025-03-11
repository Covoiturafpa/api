package fr.afpa.covoiturafpa.services.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hcaptcha")
public class HCaptchaConfig {
    
    private String secret;
    private String uri;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HCaptchaConfig() {
    }

    
}
