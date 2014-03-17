package controller;

import javax.ejb.Local;
import model.Adress;
import model.User;

@Local
public interface AdressSessionBeanLocal {

    public Adress createAdress(String street, String housenumber, String city, String country, String postalCode, boolean isShippingAdress, boolean isInvoiceAddress, User userID);

    public void changeStreet(Adress adress, String street);

    public void changeHousenumber(Adress adress, String housenumber);

    public void changeCity(Adress adress, String city);

    public void changeCountry(Adress adress, String country);

    public void changePostalcode(Adress adress, String postalCode);

    public void changeShippingAdress(Adress adress, boolean isShippingAdress);

    public void changeInvoiceAdress(Adress adress, boolean isInvoiceAddress);

}
