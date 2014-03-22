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

    /**
     * Gibt alle Autoobjekte aus der Datenbank zurück
     *
     * @return
     */
    @Override
    public List<Car> getListOfCars() {
        Query query;
        query = entityManager.createNamedQuery("Car.findAll");
        List<Car> queryResult;
        queryResult = query.getResultList();
        return queryResult;
    }

    /**
     * Sucht Autos an Hand des Modelnamens aus der Datenbank
     *
     * @param modelname ist der Modelname, der in der JSP ausgewählt wird
     * @return
     */
    @Override
    public List<Car> getListOfCarsOfSelectedModel(String modelname) {
        Query modelQuery;
        modelQuery = entityManager.createNamedQuery("Car.findByModelname");
        modelQuery.setParameter("modelname", modelname);
        List modelQueryResult = modelQuery.getResultList();
        return modelQueryResult;
    }

    /**
     * Sucht Autos an Hand des Markennamens aus der Datenbank
     *
     * @param brandname ist der Markenname, der in der JSP ausgewählt wird
     * @return
     */
    @Override
    public List<Car> getListOfCarsOfSelectedBrand(String brandname) {
        Query brandQuery;
        brandQuery = entityManager.createNamedQuery("Car.findByBrandname");
        brandQuery.setParameter("brandname", brandname);
        List modelQueryResult = brandQuery.getResultList();
        return modelQueryResult;
    }

}
