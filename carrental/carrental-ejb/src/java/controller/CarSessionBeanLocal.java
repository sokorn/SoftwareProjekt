package controller;

import java.util.List;
import javax.ejb.Local;
import model.Car;

@Local
public interface CarSessionBeanLocal
{

    public List<Car> getCarList(String name, String type);

    public Car getCarById(Integer id);

    public List<String> getNameList(String type);

    public void unBlockCar(Car car);

    public void blockCar(Car car);

}
