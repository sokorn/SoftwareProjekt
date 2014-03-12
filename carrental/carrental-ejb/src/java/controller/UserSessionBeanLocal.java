package controller;

import java.util.Date;
import javax.ejb.Local;
import model.User;

@Local
public interface UserSessionBeanLocal
{
    public User createUser(int userId, String mail, Date birthdate, String loginname, String title, String firstname, String lastname);
    
    public User login(String loginname, String passwort);
    
    public void changeFirstname(User user, String newFirstname);
    
    public void changeLastname(User user, String newLastname);
    
    public void changeMail(User user, String newLastname);
    
    public void changeTitle(User user, String newLastname);
    
}