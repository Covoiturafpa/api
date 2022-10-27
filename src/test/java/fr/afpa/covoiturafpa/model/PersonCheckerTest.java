package fr.afpa.covoiturafpa.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.afpa.covoiturafpa.Application;
import fr.afpa.covoiturafpa.model.utils.PersonChecker;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonCheckerTest {
    
    @Test
    public void should_return_email_validity() {
        String validEmail = "testemail@domain.com";
        assertTrue(PersonChecker.isValidEmail(validEmail));
    }

    @Test
    public void should_return_email_invalidity() {
        String invalidEmail = "bademail@77.s";
        assertFalse(PersonChecker.isValidEmail(invalidEmail));
    }

    @Test
    public void should_return_password_validity() {
        String validPassword = "Testreussi2!";
        assertTrue(PersonChecker.isValidPassword(validPassword));
    }

    @Test
    public void should_return_password_invalidity() {
        String invalidPassword = "testrate10";
        assertFalse(PersonChecker.isValidPassword(invalidPassword));
    }

    @Test
    public void should_return_phone_number_validity() {
        String validPhoneNumber = "+336 05 04 03 02";
        assertTrue(PersonChecker.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    public void should_return_phone_number_invalidity() {
        String invalidPhoneNumber = "o1o2o3o4o5";
        assertFalse(PersonChecker.isValidPhoneNumber(invalidPhoneNumber));
    }

    @Test
    public void should_return_period_validity() {
        LocalDate validStartActivity = LocalDate.ofYearDay(2022, 1);
        LocalDate validEndActivity = LocalDate.ofYearDay(2022, 364);
        assertTrue(PersonChecker.isValidPeriod(validStartActivity, validEndActivity));
    }

    @Test
    public void should_return_period_invalidity() {
        LocalDate invalidStartActivity = LocalDate.ofYearDay(2022, 364);
        LocalDate invalidEndActivity = LocalDate.ofYearDay(2022, 1);
        assertFalse(PersonChecker.isValidPeriod(invalidStartActivity, invalidEndActivity));
    }
}
