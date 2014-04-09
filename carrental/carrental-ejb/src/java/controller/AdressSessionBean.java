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
     * @param street
     * @param housenumber
     * @param city
     * @param country
     * @param isShippingAdress
     * @param userID
     * @param postalCode
     * @param isInvoiceAddress
     * @param region
     * @return
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
     * Gibt alle Adressen eines Benutzers als Liste zurück
     *
     * @param user
     * @return
     */
    @Override
    public List<Adress> getAdresses(User user)
    {
        Query query = entityManager.createNamedQuery("Adress.findByUserId");
        query.setParameter("userId", user);
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
     *
     * @param adress
     * @param newStreet
     */
    @Override
    public void changeStreet(Adress adress, String newStreet)
    {
        adress.setStreet(newStreet);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     *
     * @param adress
     * @param newHousenumber
     */
    @Override
    public void changeHousenumber(Adress adress, String newHousenumber)
    {
        adress.setHousenumber(newHousenumber);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     *
     * @param adress
     * @param newCity
     */
    @Override
    public void changeCity(Adress adress, String newCity)
    {
        adress.setCity(newCity);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     *
     * @param adress
     * @param newCountry
     */
    @Override
    public void changeCountry(Adress adress, String newCountry)
    {
        adress.setCountry(newCountry);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     *
     * @param adress
     * @param newPostalCode
     */
    @Override
    public void changePostalcode(Adress adress, String newPostalCode)
    {
        adress.setPostalCode(newPostalCode);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     *
     * @param adress
     * @param newRegion
     */
    @Override
    public void changeRegion(Adress adress, String newRegion)
    {
        adress.setRegion(newRegion);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     *
     * @param adress
     * @param isShippingAdress
     */
    @Override
    public void changeShippingAdress(Adress adress, boolean isShippingAdress)
    {
        adress.setIsShippingAdress(isShippingAdress);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     *
     * @param adress
     * @param isInvoiceAddress
     */
    @Override
    public void changeInvoiceAdress(Adress adress, boolean isInvoiceAddress)
    {
        adress.setIsInvoiceAddress(isInvoiceAddress);
        entityManager.merge(adress);
        entityManager.flush();
    }

    /**
     * Löscht ein Adressobjekt aus der Datenbank
     *
     * @param adress
     */
    @Override
    public void removeAdress(Adress adress)
    {
        adress = entityManager.merge(adress);
        entityManager.remove(adress);
        entityManager.flush();
    }
}
