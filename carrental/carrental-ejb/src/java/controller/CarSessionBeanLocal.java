package controller;

import java.util.List;
import javax.ejb.Local;
import model.Car;


@Local
public interface CarSessionBeanLocal {
    public List<Car> getListOfModels(Car car);
    
}
