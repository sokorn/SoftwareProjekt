package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.*;

/**
 *
 * stellt Methoden zum Umgang mit Addressobjekten bereit
 */
@Stateless(name = "AdressSessionBean")
public class AddressSessionBean implements AddressSessionBeanLocal
{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * erstellt ein Addressobjekt.
     *
     * @param street Straße
     * @param housenumber Hausnummer
     * @param city Stadt
     * @param country Land
     * @param userID Benutzerobjekt zu dem die Addresse gehört
     * @param postalCode Postleitzahl
     * @param isInvoiceAddress ist diese Addresse eine Rechnungsadresse
     * @param region Region, kann NULL sein
     * @return das erzeugte Addressobjekt
     */
    @Override
    public Address createAdress(String street, String housenumber, String city,
            String country, String postalCode,
            boolean isInvoiceAddress, String region, User userID)
    {
        Address adress = new Address(street, housenumber, city, country,
                postalCode, isInvoiceAddress, region);
        adress.setUseruserId(userID);
        entityManager.persist(adress);
        entityManager.flush();
        return adress;
    }

    /**
     * Gibt alle Addressen eines Benutzers als Liste zurück.
     *
     * @param user Benutzerobjekt, nach dessen Addressen gesucht wird
     * @return eine Liste mit Addressen
     */
    @Override
    public List<Address> getAdresses(User user)
    {
        return user.getAdressList();
    }

    /**
     * Ändert die Straße des Addressobjekts.
     *
     * @param adress das zu ändernde Addressobjekt
     * @param newStreet die neue Straße
     */
    @Override
    public void changeStreet(Address adress, String newStreet)
    {
        adress.setStreet(newStreet);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert die Hausnummer des Addressobjekts.
     *
     * @param adress das zu ändernde Addressobjekt
     * @param newHousenumber die neue Hausnummer
     */
    @Override
    public void changeHousenumber(Address adress, String newHousenumber)
    {
        adress.setHousenumber(newHousenumber);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert die Stadt des Addressobjekts.
     *
     * @param adress das zu ändernde Addressobjekt
     * @param newCity die neue Stadt
     */
    @Override
    public void changeCity(Address adress, String newCity)
    {
        adress.setCity(newCity);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert das Land des Addressobjekts.
     *
     * @param adress das zu ändernde Addressobjekt
     * @param newCountry das neue Land
     */
    @Override
    public void changeCountry(Address adress, String newCountry)
    {
        adress.setCountry(newCountry);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * ändern die Postleitzahl des Addressobjekts
     *
     * @param adress das zu ändernde Addressobjekt
     * @param newPostalCode die neue Postleitzahl
     */
    @Override
    public void changePostalcode(Address adress, String newPostalCode)
    {
        adress.setPostalCode(newPostalCode);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * ändert die Region des Addressobjekts
     *
     * @param adress das zu ändernde Addressobjekt
     * @param newRegion die neue Region
     */
    @Override
    public void changeRegion(Address adress, String newRegion)
    {
        adress.setRegion(newRegion);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert den Wert, ob es sich bei der Addresse um eine Rechnungsadresse
     * handelt.
     *
     * @param adress das zu ändernde Addressobjekt
     * @param isInvoiceAddress Boolean Wert, ob es eine Rechnungsadresse ist
     */
    @Override
    public void changeInvoiceAdress(Address adress, boolean isInvoiceAddress)
    {
        adress.setIsInvoiceAddress(isInvoiceAddress);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Löscht ein Addressobjekt aus der Datenbank.
     *
     * @param adress das zu löschende Addressobjekt
     */
    @Override
    public void removeAdress(Address adress)
    {
        adress = entityManager.merge(adress);
        entityManager.remove(adress);
        entityManager.flush();
    }
}
