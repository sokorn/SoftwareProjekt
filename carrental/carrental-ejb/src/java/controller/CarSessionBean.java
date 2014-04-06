package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Car;

/**
 *
 * stellt Methoden zum Umgang mit Autoobjekten bereit
 */

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

     /**
     * Sucht Modelnamen von Autos an Hand des ausgewählten Markennamens
     * aus der Datenbank
     *
     * @param brandname ist der Markenname, der in der JSP ausgewählt wird
     * @return
     */
    @Override
    public List<String> getNameListOfCarsOfSelectedBrand(String brandname) {
        Query brandQuery;
        brandQuery = entityManager.createNamedQuery("Car.getModelnameByBrandname");
        brandQuery.setParameter("brandname", brandname);
        List modelQueryResult = brandQuery.getResultList();
        return modelQueryResult;
    }
    
     /**
     * Setzt ein nicht verfügbares/gebuchtes Auto auf
     * verfügbar/buchbar
     *
     * @param car
     */
    @Override
    public void unBlockCar(Car car){
        car.setAvailable(true);
    }

     /**
     * Sucht ein Auto anhand seiner ID in der Datenbank
     *
     * @param id
     * @return 
     */
    @Override
    public Car getCarById(Integer id) {
        Query idQuery;
        idQuery = entityManager.createNamedQuery("Car.findByCarId");
        idQuery.setParameter("carId", id);
        List<Car> carList = idQuery.getResultList();
        if (carList.size() == 1) {
            return carList.get(0);
        } else {
            return null;
        }

    }

     /**
     * Gibt eine Liste aller Automarken aus der DB zurück
     * Gibt eine Liste von Automodellen aus der DB zurück
     * Beide Listen sind ohne Duplikate und Alphabetisch sortiert
     *
     * @param type
     * @return 
     */
    @Override
    public List<String> getNameList(String type) {
        List<String> nameList;
        if (type.equals("brand")) {
            Query brandNameQuery;
            brandNameQuery = entityManager.createNamedQuery("Car.getBrandList");
            nameList = brandNameQuery.getResultList();
            if (nameList.size() > 0) {
                return nameList;
            } else {
                return null;
            }
        } else if (type.equals("model")) {
            Query modelNameQuery;
            modelNameQuery = entityManager.createNamedQuery("Car.getModelList");
            nameList = modelNameQuery.getResultList();
            if (nameList.size() > 0) {
                return nameList;
            } else {
                return null;
            }
        }
        return null;
    }

}
