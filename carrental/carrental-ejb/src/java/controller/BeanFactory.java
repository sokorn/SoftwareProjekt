package controller;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class BeanFactory {

    public static UserSessionBeanLocal getUserSessionBean() {
        UserSessionBeanLocal userBean = null;
        try {
            InitialContext initialContext = new InitialContext();
            userBean = (UserSessionBeanLocal) initialContext.lookup("java:global/carrental/carrental-ejb/UserSessionBean!controller.UserSessionBeanLocal");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return userBean;
    }
    
    public static CarSessionBeanLocal getCarSessionBean() {
        CarSessionBeanLocal carBean = null;
        try {
            InitialContext initialContext = new InitialContext();
            carBean = (CarSessionBeanLocal) initialContext.lookup("java:global/carrental/carrental-ejb/CarSessionBean!controller.CarSessionBeanLocal");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return carBean;
    }
    
    public static RentSessionBeanLocal getRentSessionBean() {
        RentSessionBeanLocal rentBean = null;
        try {
            InitialContext initialContext = new InitialContext();
            rentBean = (RentSessionBeanLocal) initialContext.lookup("java:global/carrental/carrental-ejb/RentSessionBean!controller.RentSessionBeanLocal");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return rentBean;
    }
}
