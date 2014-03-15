package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Car;

@Stateless(name = "CarSessionBean")
public class CarSessionBean implements CarSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> getListOfCars() {
        Query query = entityManager.createNamedQuery("Car.findAll");
        List queryResult = query.getResultList();
        return queryResult;
    }

    @Override
    public List<Car> getListOfCarsOfSelectedModel(String modelname) {
        Query modelQuery = entityManager.createNamedQuery("Car.findByModelname");
        modelQuery.setParameter("modelname", modelname);
        List modelQueryResult = modelQuery.getResultList();
        return modelQueryResult;
    }

    @Override
    public List<Car> getListOfCarsOfSelectedBrand(String brandname) {
        Query brandQuery = entityManager.createNamedQuery("Car.findByBrandname");
        brandQuery.setParameter("brandname", brandname);
        List modelQueryResult = brandQuery.getResultList();
        return modelQueryResult;
    }

}
