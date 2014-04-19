package utils;

import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * stellt verschiedene Validierungsmethoden zur Verfügung.
 *
 */
public class Validator
{

    public static final String POSTALCODEREGEX = "^(?!01000|99999)(0[1-9]\\d{3}|[1-9]\\d{4})$";

    /**
     * Validiert eine Mail. Benutzt wird hierbei das Apache Package
     * CommonsValidator
     *
     * @param mail die zuprüfende Mailadresse
     * @return true, wenn die Mailadresse valide ist, sonst false
     */
    public static boolean validateMail(String mail)
    {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(mail);
    }

    /**
     * Validiert eine Postleitzahl. Hierbei wird über ein Regex Ausdruck
     * geprüft, ob die Postleitzahl einer deutschen Postleitzahl entspricht.
     *
     * @param postalCode die zu validierende Postleitzahl
     * @return true, wenn sie valide ist, sonst false
     */
    public static boolean validatePostalCode(String postalCode)
    {
        return Pattern.matches(POSTALCODEREGEX, postalCode);
    }
}
