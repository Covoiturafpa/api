package fr.afpa.covoiturafpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ConfigurationPropertiesScan
// @EnableConfigurationProperties(HCaptchaConfig.class)
public class Application {

    @Autowired
    Environment env;

    public static void main(String[] args) {
        
        SpringApplication.run(Application.class, args);
    }

    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}