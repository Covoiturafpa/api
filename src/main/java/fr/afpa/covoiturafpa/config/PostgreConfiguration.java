package fr.afpa.covoiturafpa.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Classe de configuration de la connexion à une base postgre SQL
 * 
 * TODO voir si c'est vraiment nécessaire, ne suffit-il pas de passer par les informations du "application.properties" ?
 */
@Configuration
public class PostgreConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        Logger logger = LoggerFactory.getLogger(PostgreConfiguration.class);

        try {
            String databaseUrl = environment.getProperty("spring.datasource.url");
            logger.info("URL Récupérée par PostgreConfiguration : " + databaseUrl);
            Pattern regexPattern = Pattern.compile("^jdbc:(.+)://(.+):(\\d+)/(.+)\\?password=(.+)&sslmode=require&user=(.+)$");
            Matcher matcher = regexPattern.matcher(databaseUrl);

            if (matcher.find()) {
                String host = matcher.group(2);
                logger.info("Host: " + host);
                String port = matcher.group(3);
                logger.info("Port: " + port);
                String dbName = matcher.group(4);
                logger.info("DBName:" + dbName);
                String password = matcher.group(5);
                logger.info("Password: " + password);
                String username = matcher.group(6);
                logger.info("Username: " + username);

                String dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName + "?currentSchema=covoiturAfpa&stringtype=unspecified";
                String driverClassName = environment.getProperty("spring.datasource.driver-class-name");
                dataSourceBuilder.driverClassName(driverClassName);
                dataSourceBuilder.url(dbUrl);
                dataSourceBuilder.username(username);
                dataSourceBuilder.password(password);
            }  
        } catch (PatternSyntaxException | IllegalStateException | IndexOutOfBoundsException e) {
            logger.error("Mauvaise URL de base de données. Vérifiez votre \"application.properties\"", e);
            throw new Error();
        }

        return dataSourceBuilder.build();
    }
}