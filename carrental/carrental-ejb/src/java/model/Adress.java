package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * Entität für Adressen von Benutzern
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Adress.findByUserId", query = "SELECT a FROM Adress a WHERE a.useruserId = :userId")})

public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adressId;
    private String street;
    private String housenumber;
    private String city;
    private String country;
    private String region;
    private String postalCode;
    private boolean isShippingAdress;
    private boolean isInvoiceAddress;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private User useruserId;

    public Adress() {
    }

    public Adress(String street, String housenumber, String city, String country, String postalCode, boolean isShippingAdress, boolean isInvoiceAddress, String region) {
        this.street = street;
        this.housenumber = housenumber;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.isShippingAdress = isShippingAdress;
        this.isInvoiceAddress = isInvoiceAddress;
        this.region = region;
    }

    public Integer getAdressId() {
        return adressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean getIsShippingAdress() {
        return isShippingAdress;
    }

    public void setIsShippingAdress(boolean isShippingAdress) {
        this.isShippingAdress = isShippingAdress;
    }

    public boolean getIsInvoiceAddress() {
        return isInvoiceAddress;
    }

    public void setIsInvoiceAddress(boolean isInvoiceAddress) {
        this.isInvoiceAddress = isInvoiceAddress;
    }

    public User getUseruserId() {
        return useruserId;
    }

    public void setUseruserId(User useruserId) {
        this.useruserId = useruserId;
    }
}
