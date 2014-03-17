package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import model.Adress;
import model.User;

@Stateless(name="AdressSessionBean")
public class AdressSessionBean implements AdressSessionBeanLocal {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Adress createAdress(String street, String housenumber, String city, String country, String postalCode, boolean isShippingAdress, boolean isInvoiceAddress, User userID) {
        //entityManager.setFlushMode(FlushModeType.AUTO);

        Adress adress = new Adress(street, housenumber, city, country, postalCode, isShippingAdress, isInvoiceAddress);
        adress.setUseruserId(userID);
        entityManager.persist(adress);
        entityManager.flush();
        return adress;
    }

    @Override
    public void changeStreet(Adress adress, String newStreet) {
        adress.setStreet(newStreet);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeHousenumber(Adress adress, String newHousenumber) {
        adress.setHousenumber(newHousenumber);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeCity(Adress adress, String newCity) {
        adress.setCity(newCity);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeCountry(Adress adress, String newCountry) {
        adress.setCountry(newCountry);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changePostalcode(Adress adress, String newPostalCode) {
        adress.setPostalCode(newPostalCode);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeShippingAdress(Adress adress, boolean isShippingAdress) {
        adress.setIsShippingAdress(isShippingAdress);
        entityManager.merge(this);
        entityManager.flush();
    }

    @Override
    public void changeInvoiceAdress(Adress adress, boolean isInvoiceAddress) {
        adress.setIsInvoiceAddress(isInvoiceAddress);
        entityManager.merge(this);
        entityManager.flush();
    }
}
