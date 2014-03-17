package controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Adress;
import model.User;
import utils.DateParser;
import utils.Password;

@Stateless(name = "UserSessionBean")
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(String title, String firstname, String lastname, String birthday, String mail, String password) {
            String passwordHash = Password.hashPassword(password);
            Date birthdate = DateParser.parseToDate(birthday);
            User user = new User(title, firstname, lastname, birthdate, mail, passwordHash);
            entityManager.persist(user);
            entityManager.flush();
            return user;
    }

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

    @Override
    public void addAdressToUser(User user, Adress adress) {
        user.addAdress(adress);
    }

    @Override
    public boolean mailAlreadyUsed(String mail) {
        Query query = entityManager.createNamedQuery("User.login");
        query.setParameter("login", mail);
        List queryResult = query.getResultList();
        return !queryResult.isEmpty();
    }

}
