package controller;

import javax.ejb.Local;
import model.Adress;
import model.User;

@Local
public interface UserSessionBeanLocal {

    public User createUser(String title, String firstname, String lastname, String birthday, String mail, String password);

    public void addAdressToUser(User user, Adress adress);

    public boolean mailAlreadyUsed(String mail);

    public User login(String loginname, String passwort);

    public void changeFirstname(User user, String newFirstname);

    public void changeLastname(User user, String newLastname);

    public void changeMail(User user, String newLastname);

    public void changeTitle(User user, String newLastname);

}
