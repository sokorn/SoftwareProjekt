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
 * stellt Methoden zum Umgang mit Usern bereit
 *
 *
 */
@Stateless(name = "UserSessionBean")
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * erstellt ein Userobjekt und speichert es in die Datenbank. Hierfür wird
     * das Passwort durch einen Hashwert verschlüsselt und das Geburtsdatum von
     * einem String in ein Date-Objekt geparsed
     *
     * @param title
     * @param firstname
     * @param lastname
     * @param birthday
     * @param mail
     * @param password
     * @return
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
     * @return
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

    @Override
    public void changeFirstname(User user, String newFirstname) {
        user.setFirstname(newFirstname);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeLastname(User user, String newLastname) {
        user.setFirstname(newLastname);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeMail(User user, String newMail) {
        user.setFirstname(newMail);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeTitle(User user, String newTitle) {
        user.setFirstname(newTitle);
        entityManager.merge(this);
        entityManager.flush();
    }

    // fügt einem Benutzer eine Adresse hinzu
    @Override
    public void addAdressToUser(User user, Adress adress) {
        user.addAdress(adress);
    }

    // prüft ob eine Emailadresse bereits in der Datenbank vorhanden ist
    @Override
    public boolean mailAlreadyUsed(String mail) {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", mail);
        List queryResult = query.getResultList();
        return !queryResult.isEmpty();
    }

}
