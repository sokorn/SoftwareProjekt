package controller;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.*;
import model.*;
import utils.DateParser;

/**
 *
 * stellt Methoden zum Umgang mit Buchungsobjekten bereit
 */
@Stateless(name = "RentsSessionBean")
public class RentSessionBean implements RentSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * erstellt ein Buchungsobjekt.
     *
     * @param startDate
     * @param endDate
     * @param userID
     * @param carID
     * @return
     */
    @Override
    public Rent prepareRent(String startDate, String endDate, User userID, Car carID) {
        Date sDate = DateParser.parseToDate(startDate);
        Date eDate = DateParser.parseToDate(endDate);

        Rent rent = new Rent(sDate, eDate);
        rent.setCarmodelId(carID);
        rent.setUseruserId(userID);
        rent.setLength(this.getLengthOfRent(sDate, eDate));
        rent.setTotalPrice(this.getRentPrice(carID, rent));
        return rent;
    }

    @Override
    public void persistRent(Rent rent) {
        entityManager.persist(rent);
        entityManager.flush();
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
    public Double getRentPrice(Car car, Rent rent) {
        return car.getPrice() * rent.getLength();
    }

    /**
     * setzt den Status des Autos auf nicht verfügbar/ausgeliehen
     *
     * @param car
     */
    @Override
    public void blockCar(Car car) {
        if (car.isAvailable()) {
            car.setAvailable(false);
            entityManager.merge(car);
        }
    }

    /**
     * setzt den Status des Autos auf verfügbar/nicht ausgeliehen
     *
     * @param car
     */
    @Override
    public void unBlockCar(Car car) {
        if (!car.isAvailable()) {
            car.setAvailable(true);
            entityManager.merge(car);
        }
    }

    /**
     * storniert/löscht ein Buchungsobjekt in der Datenbank
     *
     * @param rent
     */
    @Override
    public void cancelRent(Rent rent) {
        entityManager.remove(rent);
        entityManager.flush();
    }


    /**
     * ändert den Buchungsbeginn einer Buchung
     *
     * @param rent
     * @param startDate
     */
    @Override
    public void changeStartDate(Rent rent, Date startDate) {
        rent.setStartdate(startDate);
    }

    /**
     * ändert das Buchungsende einer Buchung
     *
     * @param rent
     * @param endDate
     */
    @Override
    public void changeEndDate(Rent rent, Date endDate) {
        rent.setEnddate(endDate);
    }
    
    // berechnet die Dauer der Buchung in Tagen, falls nur 1 Tag gebucht wird gib 1 zurück
    @Override
    public int getLengthOfRent(Date startDate, Date endDate) {
        int diffInDays = (int) ((endDate.getTime() - startDate.getTime())
                / (1000 * 60 * 60 * 24));
        return (diffInDays==0) ? 1 : diffInDays+1;
    }
}
