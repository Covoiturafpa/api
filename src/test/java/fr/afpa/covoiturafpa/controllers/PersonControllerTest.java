package fr.afpa.covoiturafpa.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.afpa.covoiturafpa.Application;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Trainee;
import fr.afpa.covoiturafpa.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private PersonRepository personRepository;
    
    @Test
    public void should_save_new_trainee_and_encrypt_password() {
        Trainee newTrainee = new Trainee();
        newTrainee.setPersonType("T");
        newTrainee.setFirstName("John");
        newTrainee.setSurname("Smith");
        newTrainee.setEmail("testemail@domain.com");
        newTrainee.setPassword("Testreussi2!");
        newTrainee.setPhoneNumber("+336 05 04 03 02");
        newTrainee.setStartActivity(LocalDate.ofYearDay(2022, 1));
        newTrainee.setEndActivity(LocalDate.ofYearDay(2022, 364));
        assertTrue(personController.isValidNewPerson(newTrainee));
        Person savedPerson = personController.createPerson(newTrainee);
        assertTrue(savedPerson.getPersonType().equals("T"));
        assertTrue(savedPerson.getEmail().equals("testemail@domain.com"));
        assertFalse(savedPerson.getPassword().equals("Testreussi2!"));
        assertTrue(savedPerson.getPhoneNumber().equals("+336 05 04 03 02"));
        assertTrue(personRepository.findById(savedPerson.getId()).isPresent());
        personRepository.deleteById(savedPerson.getId());
        assertTrue(personRepository.findById(savedPerson.getId()).isEmpty());
    }

    @Test
    public void should_reject_trainee_with_already_used_email() {
        Trainee newTrainee = new Trainee();
        newTrainee.setPersonType("T");
        newTrainee.setFirstName("John");
        newTrainee.setSurname("Smith");
        newTrainee.setEmail("testemail@domain.com");
        newTrainee.setPassword("Testreussi2!");
        newTrainee.setPhoneNumber("+336 05 04 03 02");
        newTrainee.setStartActivity(LocalDate.ofYearDay(2022, 1));
        newTrainee.setEndActivity(LocalDate.ofYearDay(2022, 364));
        assertTrue(personController.isValidNewPerson(newTrainee));
        Person savedPerson = personController.createPerson(newTrainee);
        assertFalse(personController.isValidNewPerson(newTrainee));
        personRepository.deleteById(savedPerson.getId());
        assertTrue(personRepository.findById(savedPerson.getId()).isEmpty());
    }

    @Test
    public void should_save_and_return_new_employee_via_request() {
//TODO: envoyer request via mock MVC
    }
}
