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
     * erstellt ein Buchungsobjekt. Wird aber noch nicht in der Datenbank
     * gespeichert. Die Datumsparameter, werden als String in die Methode
     * reingegeben und von utils.DateParser.parseToDate(Date date) in ein
     * Dateobjekt umgewandelt
     *
     * @param startDate Startdatum der Buchung
     * @param endDate Enddatum der Buchung
     * @param userId Benutzer, der die Buchung ausführt
     * @param carId Auto, dass zur Buchung gehört
     * @return das erzeugte Buchungsobjekt
     * @see utils.DateParser#parseToDate(java.lang.String)
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
     * Speichert ein Buchungsobjekt in der Datenbank.
     *
     * @param rent Ein Buchungsobjekt, dass vorher mit prepareRent erzeugt wurde
     * @see #prepareRent(java.lang.String, java.lang.String, model.User,
     * model.Car)
     */
    @Override
    public void persistRent(Rent rent)
    {
        entityManager.persist(rent);
        entityManager.flush();
    }

    /**
     * Gibt alle Buchungen eines Benutzers als Liste zurück.
     *
     * @param user Das Benutzerobjekt, nach dessen Buchungen gesucht werden soll
     * @return eine Liste mit Buchungsobjekten
     */
    @Override
    public List<Rent> getRents(User user)
    {
        return user.getRentList();
    }

    /**
     * prüft ob ein Benutzer aktive Buchungen hat. Als aktive Buchungen wird
     * eine Buchung bezeichnet, deren Enddatum größer und Startdatum kleiner,
     * als das heutige Datum sind und die Buchung nocht nicht angefangen hat.
     *
     * @param user das zu überprüfende Benutzerobjekt
     * @return true, wenn es aktive Buchungen gibt, false wenn nicht
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
     * Buchung.
     *
     * @param car Autoobjekt, von dem der Tagessatz gebraucht wird
     * @param rent Buchungsobjekts, dessen Länge benötigt wird
     * @return Multiplikation von Tagessatz des Autos und Länge der Buchung
     */
    @Override
    public Double getRentPrice(Car car, Rent rent)
    {
        return car.getPrice() * rent.getLength();
    }

    /**
     * löscht ein Buchungsobjekt in der Datenbank. Das Löschen einer aktiven
     * Buchung wird durch eine Prüfung auf der JSP unterbunden.
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
     * sucht eine Buchung an Hand ihrer ID aus der Datenbank.
     *
     * @param id ID des Buchungsobjekts
     * @return Liefert das Buchungsobjekt zurück, wenn die Datenbankabfrage kein
     * eindeutiges Ergebnis gelifert hat, wird NULL zurückgegeben.
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
     * @param startDate Startdatum der Buchung
     * @param endDate Enddatum der Buchung
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
