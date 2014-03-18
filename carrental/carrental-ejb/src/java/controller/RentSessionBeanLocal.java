package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Car;
import model.Rent;
import model.User;

@Local
public interface RentSessionBeanLocal {
    
    public Double getRentPrice(Car car);
    
    public int getLengthOfRent(Date startDate, Date endDate);
    
    public void blockCar(Car car);
    
    public Rent createRent(User user, Car car, double totalPrice, int length, Date startDate, Date endDate);
    
    public void cancelRent(Rent rent);
    
    
    
}
