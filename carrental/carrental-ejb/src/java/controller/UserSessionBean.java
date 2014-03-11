package controller;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Adress;
import model.User;

@Stateless(name = "UserSessionBean")
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    //TODO ADRESS
    @Override
    public User createUser(int userId, String mail, Date birthdate, String loginname, String title, String firstname, String lastname, Adress adress) {
        entityManager.setFlushMode(FlushModeType.AUTO);

        User user = new User(userId, mail, birthdate, loginname, title, firstname, lastname);
        entityManager.persist(user);
        user = entityManager.merge(user);
        entityManager.flush();
        return user;
    }
//TODO Passwortprüfung

    @Override
    public User login(String login, String passwort) {
        Query query = entityManager.createNamedQuery("User.findByMail");
        query.setParameter("mail", login);
        List queryResult = query.getResultList();
        return (User) queryResult.get(0);
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
}
