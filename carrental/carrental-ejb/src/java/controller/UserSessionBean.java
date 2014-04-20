package controller;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Address;
import model.Rent;
import model.User;
import utils.DateParser;
import utils.Password;

/**
 *
 * stellt Methoden zum Umgang mit Userobjekten bereit
 */
@Stateless(name = "UserSessionBean")
public class UserSessionBean implements UserSessionBeanLocal
{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * erstellt ein Userobjekt und speichert es in die Datenbank.
     *
     * @param title Titel
     * @param firstname Vorname
     * @param password Passwort, dass gehashed in der Datenbank gespeichert wird
     * @param lastname Nachname
     * @param birthday Geburtsdatum
     * @param mail EMailadresse, die zum Login benutzt wird, dadurch ist sie
     * einmalig in der Datenbank vorhanden
     *
     * @return gibt das gespeicherte Benutzerobjekt zurück
     */
    @Override
    public User createUser(String title, String firstname,
            String lastname, String birthday, String mail, String password)
    {
        String passwordHash = Password.hashPassword(password);
        Date birthdate = DateParser.parseToDate(birthday);
        User user = new User(title, firstname, lastname, birthdate, mail,
                passwordHash);
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    /**
     * prüft ob eine Emailadresse bereits in der Datenbank vorhanden ist.
     *
     * @param mail Zu prüfende Mailadresse
     * @return true, wenn die Mail schon vorhanden ist, ansonsten false
     */
    @Override
    public boolean mailAlreadyUsed(String mail)
    {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", mail);
        List queryResult = query.getResultList();
        return !queryResult.isEmpty();
    }

    /**
     * ermöglicht den Login eines Benutzers. Hierbei wird in der Datenbank nach
     * dem Loginnamen gesucht und danach das in der Datenbank vorhandene
     * Passwort mit dem eingegebenen und gehashed Passwort verglichen.
     *
     * @param login Die eigegebene Mailadresse
     * @param password Das eigegebene Passwort
     *
     * @return Gibt das Benutzerobjekt zurück, auf das die Mail und das Passwort
     * passen, wenn ein Fehler auftreten sollte, wird NULL zurück geliefert
     */
    @Override
    public User confirmUserLogin(String login, String password)
    {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", login);
        List queryResult = query.getResultList();
        if (queryResult.size() == 1)
        {
            User user = (User) queryResult.get(0);
            String hashedPassword = Password.hashPassword(password);
            if (user.getPasswordhash().equals(hashedPassword))
            {
                return user;
            } else
            {
                return null;
            }
        } else
        {
            return null;
        }
    }

    /**
     * Methode zum Ändern des Benutzerpassworts. Das alte Passwort wird gehashed
     * und mit dem in der Datenbank gespeicherten Passwort verglichen, wenn sie
     * übereinstimmen, wird das neue Passwort gehashed und in der Datenbank
     * gespeichert.
     *
     * @param user der Benutzer, desse Passwort geändert werden soll
     * @param oldPassword das alte Passwort
     * @param newPassword das neue Passwort
     *
     * @return true, wenn das Passwort erfolgreich in der Datenbank gespeichert
     * wurde
     */
    @Override
    public boolean changePassword(User user, String oldPassword,
            String newPassword)
    {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", user.getMail());
        List queryResult = query.getResultList();
        if (queryResult.size() == 1)
        {
            String oldHashedPassword = Password.hashPassword(oldPassword);
            if (user.getPasswordhash().equals(oldHashedPassword))
            {
                String newHashesPassword = Password.hashPassword(newPassword);
                user.setPasswordhash(newHashesPassword);
                entityManager.merge(user);
                return true;
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    /**
     * Ändert den Vornamen eines Benutzers.
     *
     * @param user das zu ändernde Benutzerobjekt
     * @param newFirstname der neue Vorname
     */
    @Override
    public void changeFirstname(User user, String newFirstname)
    {
        user.setFirstname(newFirstname);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     * Ändert den Nachnamen eines Benutzers.
     *
     * @param user das zu ändernde Bentzerobjekt
     * @param newLastname der neue Nachname
     */
    @Override
    public void changeLastname(User user, String newLastname)
    {
        user.setLastname(newLastname);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     * Ändert die Mailadresse eines Benutzers. Die Prüfung auf Einmaligkeit und
     * Validierung erfolgen bereits im Servlet.
     *
     * @param user das zu ändernde Benutzerobjekt
     * @param newMail die neue Mailadresse
     */
    @Override
    public void changeMail(User user, String newMail)
    {
        user.setMail(newMail);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     * Ändert den Titel eines Benutzers.
     *
     * @param user das zu ändernde Benutzerobjekt
     * @param newTitle der neue Titel
     */
    @Override
    public void changeTitle(User user, String newTitle)
    {
        user.setTitle(newTitle);
        entityManager.merge(user);
        entityManager.flush();
    }

    /**
     * fügt einem Benutzer eine Addresse hinzu.
     *
     * @param user das Benutzerobjekt, zu dem die Addresse hinzugefügt werden
     * soll
     * @param adress eine neue Addresse
     */
    @Override
    public void addAdressToUser(User user, Address adress)
    {
        user.addAdress(adress);
        entityManager.merge(user);
    }

    /**
     *
     * @param user
     * @param rent
     */
    @Override
    public void addRentToUser(User user, Rent rent)
    {
        user.addRent(rent);
        entityManager.merge(user);
    }

    /**
     * Löscht eine Buchung des Benutzers.
     *
     * @param user das Benutzerobjekt, von dem die Buchung gelöscht wird
     * @param rent das zu löschende Buchungsobjekt
     */
    @Override
    public void deleteRentFromUser(User user, Rent rent)
    {
        user.deleteRent(rent);
        entityManager.merge(user);
    }

    /**
     * löscht einen Benutzer aus der Datenbank. Im Servlet wird vorher geprüft,
     * ob der Benutzer noch aktive Buchungen hat, wenn ja kann er nicht gelöscht
     * werden
     *
     * @param user das zu löschende Benutzerobjekt
     */
    @Override
    public void removeUser(User user)
    {
        user = entityManager.merge(user);
        entityManager.remove(user);
    }
}
