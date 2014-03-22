package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Car;
import model.Rent;
import model.User;

@Local
public interface RentSessionBeanLocal {

    public Double getRentPrice(Car car, Rent rent);

    public void addTotalPriceToRent(Rent rent);

    public void addLengthToRent(Rent rent);

    public void blockCar(Car car);

    public Rent createRent(Date startDate, Date endDate, User userID, Car carID);

    public void cancelRent(Rent rent);

    public void unBlockCar(Car car);

    public void changeStartDate(Rent rent, Date startDate);

    public void changeEndDate(Rent rent, Date endDate);
}
