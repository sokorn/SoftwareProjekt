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
     * Liefert alle Autoobjekte zurück.
     *
     * @return Liste mit Autoobjekten
     */
    @Override
    public List<Car> getCarList()
    {
        Query query;
        query = entityManager.createNamedQuery("Car.findAll");
        List<Car> queryResult;
        queryResult = query.getResultList();
        return queryResult;
    }

    /**
     * Liefert Autoobjekte eines bestimmten Modells zurück.
     *
     * @param name der Modellname
     * @return eine Liste mit den Autoobjekten
     */
    @Override
    public List<Car> getCarListByModel(String name)
    {
        Query modelQuery;
        modelQuery = entityManager.createNamedQuery("Car.findByModelname");
        modelQuery.setParameter("modelname", name);
        List modelQueryResult = modelQuery.getResultList();
        return modelQueryResult;
    }

    /**
     * Liefert Autoobjekte einer bestimmten Marke zurück.
     *
     * @param name der Markenname
     * @return eine Liste mit den Autoobjekten
     */
    @Override
    public List<Car> getCarListByBrand(String name)
    {
        Query brandQuery;
        brandQuery = entityManager.createNamedQuery("Car.findByBrandname");
        brandQuery.setParameter("brandname", name);
        List brandQueryResult = brandQuery.getResultList();
        return brandQueryResult;
    }

    /**
     * Setzt ein nicht verfügbares Auto auf verfügbar.
     *
     * @param car das zu bearbeitende Autoobjekt
     */
    @Override
    public void unBlockCar(Car car)
    {
        car.setAvailable(true);
    }

    /**
     * setzt den Status des Autos auf nicht verfügbar.
     *
     * @param car das zu bearbeitende Autoobjekt
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
     * @return Autoobjekt, auf das die ID passt, wenn die Datenbankabfrage nicht
     * zurückgeliefert hat, wird NULL zurückgegeben
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
     * Gibt eine Liste der Automodellnamen zurück.
     *
     * @return eine Liste mit den Modellnamen, wenn die Datenbankabfrage nicht
     * zurückgeliefert hat, wird NULL zurückgegeben
     */
    @Override
    public List<String> getModelNameList()
    {
        List<String> nameList;
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

    /**
     * Gibt eine Liste mit Automarkennamen zurück.
     *
     * @return eine Liste mit den Markennamen, wenn die Datenbankabfrage nicht
     * zurückgeliefert hat, wird NULL zurückgegeben
     */
    @Override
    public List<String> getBrandNameList()
    {
        List<String> nameList;
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
    }

}
