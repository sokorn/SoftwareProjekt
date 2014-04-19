package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.*;

/**
 *
 * stellt Methoden zum Umgang mit Adressobjekten bereit
 */
@Stateless(name = "AdressSessionBean")
public class AdressSessionBean implements AdressSessionBeanLocal
{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * erstellt ein Adressobjekt
     *
     * @param street Straße
     * @param housenumber Hausnummer
     * @param city Stadt
     * @param country Land
     * @param isShippingAdress ist diese Adresse eine Lieferadresse
     * @param userID Benutzerobjekt zu dem die Adresse gehört
     * @param postalCode Postleitzahl
     * @param isInvoiceAddress ist diese Adresse eine Rechnungsadresse
     * @param region Region, kann NULL sein
     * @return das erzeugte Adressobjekt
     */
    @Override
    public Adress createAdress(String street, String housenumber, String city,
            String country, String postalCode, boolean isShippingAdress,
            boolean isInvoiceAddress, String region, User userID)
    {
        Adress adress = new Adress(street, housenumber, city, country,
                postalCode, isShippingAdress, isInvoiceAddress, region);
        adress.setUseruserId(userID);
        entityManager.persist(adress);
        entityManager.flush();
        return adress;
    }

    /**
     * Gibt alle Adressen eines Benutzers als Liste zurück.
     *
     * @param user Benutzerobjekt, nach dessen Adressen gesucht wird
     * @return eine Liste mit Adressen
     */
    @Override
    public List<Adress> getAdresses(User user)
    {
        return user.getAdressList();
    }

    /**
     * Ändert die Straße des Adressobjekts.
     *
     * @param adress das zu ändernde Adressobjekt
     * @param newStreet die neue Straße
     */
    @Override
    public void changeStreet(Adress adress, String newStreet)
    {
        adress.setStreet(newStreet);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert die Hausnummer des Adressobjekts.
     *
     * @param adress das zu ändernde Adressobjekt
     * @param newHousenumber die neue Hausnummer
     */
    @Override
    public void changeHousenumber(Adress adress, String newHousenumber)
    {
        adress.setHousenumber(newHousenumber);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert die Stadt des Adressobjekts.
     *
     * @param adress das zu ändernde Adressobjekt
     * @param newCity die neue Stadt
     */
    @Override
    public void changeCity(Adress adress, String newCity)
    {
        adress.setCity(newCity);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert das Land des Adressobjekts.
     *
     * @param adress das zu ändernde Adressobjekt
     * @param newCountry das neue Land
     */
    @Override
    public void changeCountry(Adress adress, String newCountry)
    {
        adress.setCountry(newCountry);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * ändern die Postleitzahl des Adressobjekts
     *
     * @param adress das zu ändernde Adressobjekt
     * @param newPostalCode die neue Postleitzahl
     */
    @Override
    public void changePostalcode(Adress adress, String newPostalCode)
    {
        adress.setPostalCode(newPostalCode);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * ändert die Region des Adressobjekts
     *
     * @param adress das zu ändernde Adressobjekt
     * @param newRegion die neue Region
     */
    @Override
    public void changeRegion(Adress adress, String newRegion)
    {
        adress.setRegion(newRegion);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert den Wert, ob es sich bei der Adresse um eine Lieferadresse
     * handelt.
     *
     * @param adress das zu ändernde Adressobjekt
     * @param isShippingAdress Boolean Wert, ob es eine Lieferadresse ist
     */
    @Override
    public void changeShippingAdress(Adress adress, boolean isShippingAdress)
    {
        adress.setIsShippingAdress(isShippingAdress);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Ändert den Wert, ob es sich bei der Adresse um eine Rechnungsadresse
     * handelt.
     *
     * @param adress das zu ändernde Adressobjekt
     * @param isInvoiceAddress Boolean Wert, ob es eine Rechnungsadresse ist
     */
    @Override
    public void changeInvoiceAdress(Adress adress, boolean isInvoiceAddress)
    {
        adress.setIsInvoiceAddress(isInvoiceAddress);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Löscht ein Adressobjekt aus der Datenbank.
     *
     * @param adress das zu löschende Adressobjekt
     */
    @Override
    public void removeAdress(Adress adress)
    {
        adress = entityManager.merge(adress);
        entityManager.remove(adress);
        entityManager.flush();
    }
}
