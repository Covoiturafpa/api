package fr.afpa.covoiturafpa.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.core.env.Environment;

@Configuration
@ConditionalOnClass(DataSource.class)
public class PostgreConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = null;
        Logger logger = LoggerFactory.getLogger(PostgreConfiguration.class);
        //postgres:jfrbnsrgohiorm:ae2c2b029f7eb97eabda6fe312bafd929d166eec5b8975240faf1ede3ee8db9c@ec2-34-247-72-29.eu-west-1.compute.amazonaws.com:5432/d91i3tabunq99f
        try {
            String databaseUrl = environment.getProperty("spring.datasource.url");
            // URI dbUri = new URI(environment.getProperty("spring.datasource.url"));
            Pattern regexPattern = Pattern.compile("(.+)");
            // :(.+):(.+)@(.+):(\d+)/(.+)
            Matcher matcher = regexPattern.matcher(databaseUrl);

            String testAbsurde = matcher.group(0);
            String username = matcher.group(1);
            String password = matcher.group(2);
            String host = matcher.group(3);
            String port = matcher.group(4);
            String dbName = matcher.group(5);

            String dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName + "?currentSchema=covoiturafpa&stringtype=unspecified";

            String driverClassName = environment.getProperty("spring.datasource.driver-class-name");
            dataSource = new DriverManagerDataSource();

            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(dbUrl);
            dataSource.setUsername(username);
            dataSource.setPassword(password);    
        } catch (PatternSyntaxException | IllegalStateException | IndexOutOfBoundsException e) {
            logger.error("Mauvaise URL de base de données. Vérifiez votre \"application.properties\"", e);
            throw new Error();
        }

        return dataSource;
    }
}