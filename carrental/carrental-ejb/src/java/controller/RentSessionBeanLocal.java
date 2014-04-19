package controller;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import model.Car;
import model.Rent;
import model.User;

@Local
public interface RentSessionBeanLocal
{

    public Rent prepareRent(String startDate, String endDate,
            User userID, Car carID);

    public void persistRent(Rent rent);

    public List<Rent> getRents(User user);

    public boolean hasActiveRents(User user);

    public Double getRentPrice(Car car, Rent rent);

    public void cancelRent(Rent rent);

    public Rent getRentById(Integer id);

    public int getLengthOfRent(Date startDate, Date endDate);

}
