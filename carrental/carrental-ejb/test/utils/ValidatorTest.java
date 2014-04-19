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
        assertEquals(true, Validator.validateMail("test@test.com"));
        assertEquals(false, Validator.validateMail("test@test"));
        assertEquals(false, Validator.validateMail("test.test@test"));
    }

    /**
     * Test für die validatePostalCode Methode.
     */
    @Test
    public void testValidatePostalCode()
    {
        assertEquals(true, Validator.validatePostalCode("76131"));
        assertEquals(false, Validator.validatePostalCode("ABCDs"));
        assertEquals(false, Validator.validatePostalCode("1234"));
        assertEquals(false, Validator.validatePostalCode("111234"));
    }

}
