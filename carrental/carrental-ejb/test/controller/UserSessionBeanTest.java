package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.embeddable.EJBContainer;
import model.Adress;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserSessionBeanTest {

    public UserSessionBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createUser method, of class UserSessionBean.
     */
    /*@Test
     public void testCreateUser() throws Exception {
     System.out.println("createUser");
     String mail = "marco.rentschler@arcor.de";
     SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy"); 
     Date birthdate = dt.parse("29-12-1986");
     String loginname = "qler";
     String title = "Mr.";
     String firstname = "marco";
     String lastname = "rentschler";
     String passwordhash = "12345";
     Adress adress = new Adress("Kaiser-Friedrich Straße", "82", "Pforzheim", "germany", "75172", true, true);
     EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
     UserSessionBeanLocal instance = (UserSessionBeanLocal)container.getContext().lookup("java:global/classes/UserSessionBean");
     User expResult = new User("marco.rentschler@arcor.de",birthdate,"qler","Mr.","marco","rentschler", passwordhash, adress);
     User result = instance.createUser(mail, birthdate, loginname, title, firstname, lastname, passwordhash, adress);
     assertEquals(expResult, result);
     container.close();

     }*/
    /**
     * Test of login method, of class UserSessionBean.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String login = "";
        String passwort = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserSessionBeanLocal instance = (UserSessionBeanLocal) container.getContext().lookup("java:global/classes/UserSessionBean");
        User expResult = null;
        User result = instance.login(login, passwort);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeFirstname method, of class UserSessionBean.
     */
    @Test
    public void testChangeFirstname() throws Exception {
        System.out.println("changeFirstname");
        User user = null;
        String newFirstname = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserSessionBeanLocal instance = (UserSessionBeanLocal) container.getContext().lookup("java:global/classes/UserSessionBean");
        instance.changeFirstname(user, newFirstname);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeLastname method, of class UserSessionBean.
     */
    @Test
    public void testChangeLastname() throws Exception {
        System.out.println("changeLastname");
        User user = null;
        String newLastname = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserSessionBeanLocal instance = (UserSessionBeanLocal) container.getContext().lookup("java:global/classes/UserSessionBean");
        instance.changeLastname(user, newLastname);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeMail method, of class UserSessionBean.
     */
    @Test
    public void testChangeMail() throws Exception {
        System.out.println("changeMail");
        User user = null;
        String newMail = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserSessionBeanLocal instance = (UserSessionBeanLocal) container.getContext().lookup("java:global/classes/UserSessionBean");
        instance.changeMail(user, newMail);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeTitle method, of class UserSessionBean.
     */
    @Test
    public void testChangeTitle() throws Exception {
        System.out.println("changeTitle");
        User user = null;
        String newTitle = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserSessionBeanLocal instance = (UserSessionBeanLocal) container.getContext().lookup("java:global/classes/UserSessionBean");
        instance.changeTitle(user, newTitle);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
