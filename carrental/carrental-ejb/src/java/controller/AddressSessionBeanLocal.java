package controller;

import java.util.List;
import javax.ejb.Local;
import model.Address;
import model.User;

@Local
public interface AddressSessionBeanLocal
{

    public Address createAdress(String street, String housenumber, String city,
            String country, String postalCode,
            boolean isInvoiceAddress, String region, User userID);

    public List<Address> getAdresses(User user);

    public void changeStreet(Address adress, String street);

    public void changeHousenumber(Address adress, String housenumber);

    public void changeCity(Address adress, String city);

    public void changeCountry(Address adress, String country);

    public void changePostalcode(Address adress, String postalCode);

    public void changeRegion(Address adress, String newRegion);

    public void changeInvoiceAdress(Address adress, boolean isInvoiceAddress);

    public void removeAdress(Address adress);

}
