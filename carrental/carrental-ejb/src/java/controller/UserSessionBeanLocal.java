package controller;

import javax.ejb.Local;
import model.Adress;
import model.User;

@Local
public interface UserSessionBeanLocal
{

    public User createUser(String title, String firstname, String lastname,
            String birthday, String mail, String password);

    public boolean mailAlreadyUsed(String mail);

    public User confirmUserLogin(String loginname, String passwort);

    public boolean changePassword(User user, String oldPassword,
            String newPassword);

    public void changeFirstname(User user, String newFirstname);

    public void changeLastname(User user, String newLastname);

    public void changeMail(User user, String newLastname);

    public void changeTitle(User user, String newLastname);

    public void addAdressToUser(User user, Adress adress);

    public void removeUser(User user);

}
