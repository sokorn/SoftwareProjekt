package controller;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Car;
import model.Rent;
import model.User;

@Stateless(name = "RentsSessionBean")
public class RentSessionBean implements RentSessionBeanLocal {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Double getRentPrice(Car car, Rent rent) {
        return car.getPrice()*rent.getLength();
    }

    @Override
    public int getLengthOfRent(Rent rent) {
        int diffInDays = (int)( (rent.getEnddate().getTime() - rent.getStartdate().getTime()) 
                 / (1000 * 60 * 60 * 24) );
        return diffInDays;
    }

    @Override
    public void blockCar(Car car) {
        if(car.getAvailable()){
            car.setAvailable(false);
        }
    }

    @Override
    public Rent createRent(Date startDate, Date endDate) {
            Rent rent = new Rent(startDate, endDate);
            entityManager.persist(endDate);
            entityManager.flush();
            return rent;
    }

    @Override
    public void cancelRent(Rent rent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
