package controller;

import java.util.List;
import javax.ejb.Stateless;
import model.Car;

@Stateless(name = "CarSessionBean")
public class CarSessionBean implements CarSessionBeanLocal {
  
    @Override
    public List<Car> getListOfModels(Car car) {
       return null;
    }
}
