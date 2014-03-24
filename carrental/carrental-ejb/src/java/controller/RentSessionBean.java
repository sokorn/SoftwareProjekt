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
        }
    }

    /**
     * erstellt ein Buchungsobjekt und speichert es in die Datebank
     *
     * @param startDate
     * @param endDate
     * @param userID
     * @param carID
     * @return
     */
    @Override
    public Rent createRent(String startDate, String endDate, User userID, Car carID) {
        Rent rent = new Rent(DateParser.parseToDate(startDate), DateParser.parseToDate(endDate));
        rent.setCarmodelId(carID);
        rent.setUseruserId(userID);
        entityManager.persist(rent);
        entityManager.flush();
        return rent;
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
     * fügt einem Buchungsobjekt seinen Gesamtpreis hinzu
     *
     * @param rent
     */
    @Override
    public void addTotalPriceToRent(Rent rent) {
        rent.setTotalPrice(rent.getLengthOfRent(rent));
    }

    /**
     * fügt einem Buchungsobjekt seine Buchungsdauer hinzu
     *
     * @param rent
     */
    @Override
    public void addLengthToRent(Rent rent) {
        rent.setLength(rent.getLengthOfRent(rent));
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

}
