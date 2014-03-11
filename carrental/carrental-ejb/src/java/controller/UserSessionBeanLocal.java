package controller;

import java.util.Date;
import javax.ejb.Local;
import model.User;

@Local
public interface UserSessionBeanLocal
{
    public User createUser(String name, Date birthdate, String loginname, String title, String firstname, String lastname);
    
    public User login(String login, String passwort);
}