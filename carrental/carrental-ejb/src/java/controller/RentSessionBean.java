package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import model.*;
import utils.DateParser;

/**
 *
 * stellt Methoden zum Umgang mit Buchungsobjekten bereit
 */
@Stateless(name = "RentsSessionBean")
public class RentSessionBean implements RentSessionBeanLocal
{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * erstellt ein Buchungsobjekt.
     *
     * @param startDate
     * @param endDate
     * @param userId
     * @param carId
     * @return
     */
    @Override
    public Rent prepareRent(String startDate, String endDate,
            User userId, Car carId)
    {
        Date sDate = DateParser.parseToDate(startDate);
        Date eDate = DateParser.parseToDate(endDate);

        Rent rent = new Rent(sDate, eDate);
        rent.setCarmodelId(carId);
        rent.setUseruserId(userId);
        rent.setLength(this.getLengthOfRent(sDate, eDate));
        rent.setTotalPrice(this.getRentPrice(carId, rent));
        return rent;
    }

    /**
     * Speichert ein Buchungsobjekt in der DB
     *
     * @param rent
     */
    @Override
    public void persistRent(Rent rent)
    {
        entityManager.persist(rent);
        entityManager.flush();
    }

    /**
     * Gibt alle Buchungen eines Benutzers als Liste zurück
     *
     * @param user
     * @return
     */
    @Override
    public List<Rent> getRents(User user)
    {
        Query query = entityManager.createNamedQuery("Rent.findByUser");
        query.setParameter("useruserId", user);
        List queryResult = query.getResultList();
        if (queryResult.size() > 0)
        {
            return queryResult;
        } else
        {
            return null;
        }
    }

    /**
     * prüft ob ein Benutzer aktive Buchungen hat
     *
     * @param user
     * @return
     */
    @Override
    public boolean hasActiveRents(User user)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(new Date());
        Date date = DateParser.parseToDate(dateString);
        Query query = entityManager.createNamedQuery("Rent.findActiveRents");
        query.setParameter("useruserId", user);
        query.setParameter("date", date);
        List queryResult = query.getResultList();
        return queryResult.size() > 0;

    }

    /**
     * berechnet den Buchungspreis anhand des Tagessatzes und der Dauer der
     * Buchung
     *
     * @param car
     * @param rent
     * @return
     */
    @Override
    public Double getRentPrice(Car car, Rent rent)
    {
        return car.getPrice() * rent.getLength();
    }

    /**
     * storniert/löscht ein Buchungsobjekt in der Datenbank
     *
     * @param rent Die zu löschende Buchung
     */
    @Override
    public void cancelRent(Rent rent)
    {
        rent = entityManager.merge(rent);
        entityManager.remove(rent);
        entityManager.flush();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Rent getRentById(Integer id)
    {
        Query idQuery;
        idQuery = entityManager.createNamedQuery("Rent.findByRentId");
        idQuery.setParameter("rentId", id);
        List<Rent> rentList = idQuery.getResultList();
        if (rentList.size() == 1)
        {
            return rentList.get(0);
        } else
        {
            return null;
        }

    }

    /**
     * berechnet die Dauer der Buchung in Tagen.
     *
     * @param startDate
     * @param endDate
     * @return Gibt die Differenz in Tagen zurück, wenn Start- und Enddatum
     * gleich sind, wird 1 zurück geliefert
     */
    @Override
    public int getLengthOfRent(Date startDate, Date endDate)
    {
        int diffInDays = (int) ((endDate.getTime() - startDate.getTime())
                / (1000 * 60 * 60 * 24));
        return (diffInDays == 0) ? 1 : diffInDays + 1;
    }
}
