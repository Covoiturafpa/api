package fr.afpa.covoiturafpa.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.core.env.Environment;

@Configuration
public class PostgreConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = null;
        Logger logger = LoggerFactory.getLogger(PostgreConfiguration.class);

        try {
            String databaseUrl = environment.getProperty("spring.datasource.url");
            Pattern regexPattern = Pattern.compile("^(.+):(.+):(.+)@(.+):(\\d+)/(.+)$");
            Matcher matcher = regexPattern.matcher(databaseUrl);

            if (matcher.find()) {
                String username = matcher.group(2);
                String password = matcher.group(3);
                String host = matcher.group(4);
                String port = matcher.group(5);
                String dbName = matcher.group(6);

                String dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName + "?currentSchema=covoiturafpa&stringtype=unspecified";
                String driverClassName = environment.getProperty("spring.datasource.driver-class-name");
                dataSource = new DriverManagerDataSource();

                dataSource.setDriverClassName(driverClassName);
                dataSource.setUrl(dbUrl);
                dataSource.setUsername(username);
                dataSource.setPassword(password);
            }  
        } catch (PatternSyntaxException | IllegalStateException | IndexOutOfBoundsException e) {
            logger.error("Mauvaise URL de base de données. Vérifiez votre \"application.properties\"", e);
            throw new Error();
        }

        return dataSource;
    }
}