package fr.afpa.covoiturafpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class Application {

    @Autowired
    Environment env;

    public static void main(String[] args) {
        
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        // TODO supprimez moi une fois le déploiement réussi
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.info("Server port from properties : " + env.getProperty("server.port"));
        logger.info("Spring data source from properties : " + env.getProperty("spring.datasource.url"));
        logger.info("Spring db user from properties : " + env.getProperty("spring.datasource.username"));
        logger.info("Spring db pwd from properties : " + env.getProperty("spring.datasource.password"));
    }

    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}