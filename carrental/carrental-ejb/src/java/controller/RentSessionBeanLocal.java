package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Car;
import model.Rent;
import model.User;

@Local
public interface RentSessionBeanLocal {
    
    public Double getRentPrice(Car car, Rent rent);
    
    public int getLengthOfRent(Rent rent);
    
    public void blockCar(Car car);
    
    public Rent createRent(Date startDate, Date endDate);
    
    public void cancelRent(Rent rent);
    
    
    
}
