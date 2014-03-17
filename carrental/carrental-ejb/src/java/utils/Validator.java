package utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;

public class Validator {

    public static boolean validateMail(String mail) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(mail);
    }

    public static boolean validateCreditCard(String number) {
        CreditCardValidator cardValidator = new CreditCardValidator(CreditCardValidator.AMEX
                + CreditCardValidator.VISA + CreditCardValidator.MASTERCARD);
        return cardValidator.isValid(number);
    }

    public static boolean validateIBAN(String iban) {
        IBANCheckDigit iBANCheckDigit = new IBANCheckDigit();
        return iBANCheckDigit.isValid(iban);
    }
}
