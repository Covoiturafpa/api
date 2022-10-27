//REGEX pour l'email : https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
//REGEX pour le mot de passe : https://mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/

package fr.afpa.covoiturafpa.model.utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

import fr.afpa.covoiturafpa.model.Person;

public class PersonChecker {
    
    public static boolean hasValidFields(Person person) {
        return false;
    }

    public static boolean isValidEmail(String email) {
        return Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$").matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$").matcher(password).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.compile("^(+33|0|0033)[1-9]([. ]?[0-9]{2}){4}").matcher(phoneNumber).matches();
    }

    public static boolean hasValidPeriod(LocalDate startActivity, LocalDate endActivity) {
        return startActivity.isBefore(endActivity);
    }
}
