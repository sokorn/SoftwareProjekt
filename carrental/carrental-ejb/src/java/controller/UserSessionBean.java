package controller;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Adress;
import model.User;
import utils.DateParser;
import utils.Password;

/**
 *
 * stellt Methoden zum Umgang mit Userobjekten bereit
 */
@Stateless(name = "UserSessionBean")
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * erstellt ein Userobjekt und speichert es in die Datenbank.
     *
     * @param title Titel des Users
     * @param firstname Vorname des Users
     * @param password gehashed Passwort
     * @param lastname Nachname des Users
     * @param birthday Geburtsdatum des Users
     * @param mail EMailadresse, sowie Login des Users
     *
     * @return gibt das gespeicherte Userobjekt an das Servlet zurück
     */
    @Override
    public User createUser(String title, String firstname, String lastname, String birthday, String mail, String password) {
        String passwordHash = Password.hashPassword(password);
        Date birthdate = DateParser.parseToDate(birthday);
        User user = new User(title, firstname, lastname, birthdate, mail, passwordHash);
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    /**
     * ermöglicht den Login eines Benutzers. Hierbei wird in der Datenbank nach
     * dem Loginnamen gesucht und danach das in der Datenbank vorhandene
     * Passwort mit dem eingegebenen und gehashed Passwort verglichen.
     *
     * @param login
     * @param password
     * @return Gibt das Userobjekt zurück, auf das die Mail und das Passwort
     * passen
     */
    @Override
    public User login(String login, String password) {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", login);
        List queryResult = query.getResultList();
        if (queryResult.size() == 1) {
            User user = (User) queryResult.get(0);
            String hashedPassword = Password.hashPassword(password);
            if (user.getPasswordhash().equals(hashedPassword)) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    @Override
    public boolean confirmPassword(String login, String password) {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", login);
        List queryResult = query.getResultList();
        if (queryResult.size() == 1) {
            User user = (User) queryResult.get(0);
            String hashedPassword = Password.hashPassword(password);
            return user.getPasswordhash().equals(hashedPassword);
        } else {
            return false;
        }
    }

    /**
     *
     * @param user
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", user.getMail());
        List queryResult = query.getResultList();
        if (queryResult.size() == 1) {
            String oldHashedPassword = Password.hashPassword(oldPassword);
            if (user.getPasswordhash().equals(oldHashedPassword)) {
                String newHashesPassword = Password.hashPassword(newPassword);
                user.setPasswordhash(newHashesPassword);
                entityManager.merge(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @param user
     * @param newFirstname
     */
    @Override
    public void changeFirstname(User user, String newFirstname) {
        user.setFirstname(newFirstname);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     *
     * @param user
     * @param newLastname
     */
    @Override
    public void changeLastname(User user, String newLastname) {
        user.setLastname(newLastname);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     *
     * @param user
     * @param newMail
     */
    @Override
    public void changeMail(User user, String newMail) {
        user.setMail(newMail);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     *
     * @param user
     * @param newTitle
     */
    @Override
    public void changeTitle(User user, String newTitle) {
        user.setTitle(newTitle);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     * fügt einem Benutzer eine Adresse hinzu.
     *
     * @param user
     * @param adress
     */
    @Override
    public void addAdressToUser(User user, Adress adress) {
        user.addAdress(adress);
    }

    /**
     * prüft ob eine Emailadresse bereits in der Datenbank vorhanden ist.
     *
     * @param mail Zu prüfende Mailadresse
     * @return
     */
    @Override
    public boolean mailAlreadyUsed(String mail) {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", mail);
        List queryResult = query.getResultList();
        return !queryResult.isEmpty();
    }

    /**
     * löscht einen Benutzer aus der Datenbank
     *
     * @param user
     */
    @Override
    public void removeUser(User user) {
        user = entityManager.merge(user);
        entityManager.remove(user);
        entityManager.flush();
    }
}
