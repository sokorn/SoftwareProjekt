package controller;

import javax.ejb.Local;
import model.Address;
import model.Rent;
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

    public void addAdressToUser(User user, Address adress);

    public void addRentToUser(User user, Rent rent);

    public void deleteRentFromUser(User user, Rent rent);

    public void removeUser(User user);

}
