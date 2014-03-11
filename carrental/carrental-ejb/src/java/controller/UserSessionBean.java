package controller;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import model.User;

@Stateless(name = "SessionBean")
public class UserSessionBean implements UserSessionBeanLocal {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(String name, Date birthdate, String loginname, String title, String firstname, String lastname) {
      entityManager.setFlushMode(FlushModeType.AUTO);
        User user = new User(name, birthdate, loginname, title, firstname, lastname);
        entityManager.persist(user);
        user = entityManager.merge(user);
        entityManager.flush();
        return user;
    }

    @Override
    public User login(String login, String passwort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
