package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class ValidatorTest
{
    
    public ValidatorTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of validateMail method, of class Validator.
     */
    @Test
    public void testValidateMail()
    {
        System.out.println("validateMail");
        String mail = "";
        boolean expResult = false;
        boolean result = Validator.validateMail(mail);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validatePostalCode method, of class Validator.
     */
    @Test
    public void testValidatePostalCode()
    {
        System.out.println("validatePostalCode");
        String postalCode = "";
        boolean expResult = false;
        boolean result = Validator.validatePostalCode(postalCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
