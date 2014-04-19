package controller;

import java.util.List;
import javax.ejb.Local;
import model.Car;

@Local
public interface CarSessionBeanLocal
{

    public List<Car> getCarList();

    public List<Car> getCarListByModel(String name);

    public List<Car> getCarListByBrand(String name);

    public void unBlockCar(Car car);

    public void blockCar(Car car);

    public Car getCarById(Integer id);

    public List<String> getModelNameList();

    public List<String> getBrandNameList();

}
