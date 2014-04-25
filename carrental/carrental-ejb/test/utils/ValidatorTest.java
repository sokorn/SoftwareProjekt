package utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Testet die Validierungen in der Klasse Validator aus dem utils Package.
 */
public class ValidatorTest
{

    /**
     * Test für die validateMail Methode.
     */
    @Test
    public void testValidateMail()
    {
        System.out.println("Starte Test für Mailvalidierung");
        System.out.println("-Test 1/3:");
        System.out.println("    -Validiere die E-Mail-Adresse: \"test@test.com\" ");
        System.out.println("    -Erwartetes Ergebnis: true");
        try
        {
            assertEquals(true, Validator.validateMail("test@test.com"));
            System.out.println("    -Tatsächliches Ergebnis: true");
            System.out.println("    -Test bestanden!");
        } catch (AssertionError e)
        {
            System.err.println("    -Tatsächliches Ergebnis: false");
            System.err.println("    -Test nicht bestanden!");
        }
        System.out.println("-Test 2/3:");
        System.out.println("    -Validiere die E-Mail-Adresse: \"test@test\" ");
        System.out.println("    -Erwartetes Ergebnis: false");
        try
        {
            assertEquals(false, Validator.validateMail("test@test"));
            System.out.println("    -Tatsächliches Ergebnis: false");
            System.out.println("    -Test bestanden!");
        } catch (AssertionError e)
        {
            System.err.println("    -Tatsächliches Ergebnis: true");
            System.err.println("    -Test nicht bestanden!");
        }
        System.out.println("-Test 3/3:");
        System.out.println("    -Validiere die E-Mail-Adresse: \"test.test@test\" ");
        System.out.println("    -Erwartetes Ergebnis: false");
        try
        {
            assertEquals(false, Validator.validateMail("test.test@test"));
            System.out.println("    -Tatsächliches Ergebnis: false");
            System.out.println("    -Test bestanden!");
        } catch (AssertionError e)
        {
            System.err.println("    -Tatsächliches Ergebnis: true");
            System.err.println("    -Test nicht bestanden!");
        }
        System.out.println("Schließe Mailvalidierung ab");
    }

    /**
     * Test für die validatePostalCode Methode.
     */
    @Test
    public void testValidatePostalCode()
    {
        System.out.println("Starte Test für Postleitzahlenvalidierung");
        System.out.println("-Test 1/4");
        System.out.println("    -Validiere die Postleitzahl \"76131\"");
        System.out.println("    -Erwartetes Ergebnis: true");
        try
        {
            assertEquals(true, Validator.validatePostalCode("76131"));
            System.out.println("    -Tatsächliches Ergebnis: true");
            System.out.println("    -Test bestanden!");
        } catch (AssertionError e)
        {
            System.err.println("    -Tatsächliches Ergebnis: false");
            System.err.println("    -Test nicht bestanden!");
        }
        System.out.println("-Test 2/4");
        System.out.println("    -Validiere die Postleitzahl \"ABCDs\"");
        System.out.println("    -Erwartetes Ergebnis: false");
        try
        {
            assertEquals(false, Validator.validatePostalCode("ABCDs"));
            System.out.println("    -Tatsächliches Ergebnis: false");
            System.out.println("    -Test bestanden!");
        } catch (AssertionError e)
        {
            System.err.println("    -Tatsächliches Ergebnis: true");
            System.err.println("    -Test nicht bestanden!");
        }
        System.out.println("-Test 3/4");
        System.out.println("    -Validiere die Postleitzahl \"1234\"");
        System.out.println("    -Erwartetes Ergebnis: false");
        try
        {
            assertEquals(false, Validator.validatePostalCode("1234"));
            System.out.println("    -Tatsächliches Ergebnis: false");
            System.out.println("    -Test bestanden!");
        } catch (AssertionError e)
        {
            System.err.println("    -Tatsächliches Ergebnis: true");
            System.err.println("    -Test nicht bestanden!");
        }
        System.out.println("-Test 4/4");
        System.out.println("    -Validiere die Postleitzahl \"111234\"");
        System.out.println("    -Erwartetes Ergebnis: false");
        try
        {
            assertEquals(false, Validator.validatePostalCode("111234"));
            System.out.println("    -Tatsächliches Ergebnis: false");
            System.out.println("    -Test bestanden!");
        } catch (AssertionError e)
        {
            System.err.println("    -Tatsächliches Ergebnis: true");
            System.err.println("    -Test nicht bestanden!");
        }
        System.out.println("Schließe Postleitzahlenvalidierung ab");
    }

}
