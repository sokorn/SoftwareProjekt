package controller;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.*;
import model.*;


// stellt methoden zum Umgang mit Buchungsobjekten bereit
@Stateless(name = "RentsSessionBean")
public class RentSessionBean implements RentSessionBeanLocal {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    // berechnet den Buchungspreis anhand des Tagessatzes und der Dauer der Buchung
    @Override
    public Double getRentPrice(Car car, Rent rent) {
        return car.getPrice()*rent.getLength();
    }
    
    // setzt den Status des Autos auf nicht verfügbar/ausgeliehen
    @Override
    public void blockCar(Car car) {
        if(car.getAvailable()){
            car.setAvailable(false);
        }
    }
    
    // setzt den Status des Autos auf verfügbar/nicht ausgeliehen
    @Override
    public void unBlockCar(Car car) {
        if(!car.getAvailable()){
            car.setAvailable(true);
        }
    }
    
    // erstellt ein Buchungsobjekt und speichert es in die Datebank
    @Override
    public Rent createRent(Date startDate, Date endDate, User userID, Car carID) {
            Rent rent = new Rent(startDate, endDate);
            rent.setCarcarId(carID);
            rent.setUseruserId(userID);
            entityManager.persist(rent);
            entityManager.flush();
            return rent;
    }
    
    // storniert/löscht ein Buchungsobjekt in der Datenbank
    @Override
    public void cancelRent(Rent rent) {
        entityManager.remove(rent);
        entityManager.flush();
    }

    // fügt einem Buchungsobjekt seinen Gesamtpreis hinzu
    @Override
    public void addTotalPriceToRent(Rent rent) {
        rent.setTotalPrice(rent.getLengthOfRent(rent));
    }

    // fügt einem Buchungsobjekt seine Buchungsdauer hinzu
    @Override
    public void addLengthToRent(Rent rent) {
        rent.setLength(rent.getLengthOfRent(rent));
    }

    // ändert den Buchungsbeginn einer Buchung
    @Override
    public void changeStartDate(Rent rent, Date startDate) {
        rent.setStartdate(startDate);
    }
    
    // ändert das Buchungsende einer Buchung
    @Override
    public void changeEndDate(Rent rent, Date endDate) {
        rent.setEnddate(endDate);
    }
    
}
