package utils;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * stellt verschiedene Validatormethoden zur Verfügung
 *
 */
public class Validator
{

    /**
     * Validiert eine Mail
     *
     * @param mail
     * @return
     */
    public static boolean validateMail(String mail)
    {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(mail);
    }
}
