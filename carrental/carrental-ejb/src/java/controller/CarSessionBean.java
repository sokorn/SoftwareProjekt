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
public class CarSessionBean implements CarSessionBeanLocal
{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Sucht Autos an Hand des Namens und Typs aus der Datenbank.
     *
     *
     * @param name Bezieht sich sowohl auf Model- als auch auf Markenname
     * @param type Mögliche Ausprägungen "model","brand" oder "all"
     * @return Liste mit Car-Objekten
     */
    @Override
    public List<Car> getCarList(String name, String type)
    {
        switch (type)
        {
            case "model":
            {
                Query modelQuery;
                modelQuery = entityManager.createNamedQuery("Car.findByModelname");
                modelQuery.setParameter("modelname", name);
                List modelQueryResult = modelQuery.getResultList();
                return modelQueryResult;
            }
            case "brand":
            {
                Query brandQuery;
                brandQuery = entityManager.createNamedQuery("Car.findByBrandname");
                brandQuery.setParameter("brandname", name);
                List brandQueryResult = brandQuery.getResultList();
                return brandQueryResult;
            }
            case "all":
            {
                Query query;
                query = entityManager.createNamedQuery("Car.findAll");
                List<Car> queryResult;
                queryResult = query.getResultList();
                return queryResult;
            }
            default:
                return null;
        }
    }

    /**
     * Setzt ein nicht verfügbares/gebuchtes Auto auf verfügbar/buchbar
     *
     * @param car das zu bearbeitende Car-Objekt
     */
    @Override
    public void unBlockCar(Car car)
    {
        car.setAvailable(true);
    }

    /**
     * setzt den Status des Autos auf nicht verfügbar/ausgeliehen
     *
     * @param car
     */
    @Override
    public void blockCar(Car car)
    {
        if (car.isAvailable())
        {
            car.setAvailable(false);
            entityManager.merge(car);
        }
    }

    /**
     * Sucht ein Auto anhand seiner ID in der Datenbank.
     *
     * @param id CarId des Autos
     * @return Car-Objekt, auf das die ID passt
     */
    @Override
    public Car getCarById(Integer id)
    {
        Query idQuery;
        idQuery = entityManager.createNamedQuery("Car.findByCarId");
        idQuery.setParameter("carId", id);
        List<Car> carList = idQuery.getResultList();
        if (carList.size() == 1)
        {
            return carList.get(0);
        } else
        {
            return null;
        }

    }

    /**
     * Gibt eine Liste aller Automarken aus der DB zurück Gibt eine Liste von
     * Automodellen aus der DB zurück Beide Listen sind ohne Duplikate und
     * Alphabetisch sortiert
     *
     * @param type
     * @return
     */
    @Override
    public List<String> getNameList(String type)
    {
        List<String> nameList;
        switch (type)
        {
            case "brand":
                Query brandNameQuery;
                brandNameQuery = entityManager.createNamedQuery("Car.getBrandList");
                nameList = brandNameQuery.getResultList();
                if (nameList.size() > 0)
                {
                    return nameList;
                } else
                {
                    return null;
                }
            case "model":
                Query modelNameQuery;
                modelNameQuery = entityManager.createNamedQuery("Car.getModelList");
                nameList = modelNameQuery.getResultList();
                if (nameList.size() > 0)
                {
                    return nameList;
                } else
                {
                    return null;
                }
        }
        return null;
    }

}
