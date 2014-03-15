package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Adress;
import model.User;

@Local
public interface UserSessionBeanLocal {

    public User createUser(String title, String firstname, String lastname, String birthday, String mail1, String mail2, String password1, String password2, Adress adress);

    public User login(String loginname, String passwort);

    public void changeFirstname(User user, String newFirstname);

    public void changeLastname(User user, String newLastname);

    public void changeMail(User user, String newLastname);

    public void changeTitle(User user, String newLastname);

}
