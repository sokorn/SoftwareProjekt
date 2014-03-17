package controller;

import java.util.List;
import javax.ejb.Local;
import model.Car;


@Local
public interface CarSessionBeanLocal {
    
    public List<Car> getListOfCars();
    
    public List<Car> getListOfCarsOfSelectedModel(String modelname);
    
    public List<Car> getListOfCarsOfSelectedBrand(String brandname);
    
}
