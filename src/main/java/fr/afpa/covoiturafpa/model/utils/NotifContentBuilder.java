package fr.afpa.covoiturafpa.model.utils;

import fr.afpa.covoiturafpa.model.Employee;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.model.Ride;
import fr.afpa.covoiturafpa.model.Trainee;

public class NotifContentBuilder {

    public static String createNewEmployeeContent(Employee employee) {
        return "Un nouveau membre du personnel, " + employee.getShowedName() + ", a créé un compte.";
    }

    public static String createNewTraineeContent(Trainee trainee) {
        return trainee.getShowedName() + " en " + trainee.getFormation().getName() + " a créé un compte.";
    }

    public static String createNewBookingContent(Person person, Ride ride) {
        return person.getShowedName() + " est intéressé.e par votre trajet " + ride.getDestination().getTravel() + ". Vous pouvez l’appeler au <a href='tel:+33.6.01.02.03.04'>" + person.getPhoneNumber() + "</a>" + " pour vous organiser.";
    }

    public static String createAcceptedBookingContent(Ride ride) {
        return ride.getDriver().getShowedName() + " vient d’accepter votre demande de trajet. Bon covoiturage !";
    }

    public static String createRejectedBookingContent(Ride ride) {
        return ride.getDriver().getShowedName() + " n’a pas accepté votre demande de trajet. D’autres sont sûrement disponibles !";
    }
}
