/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marco
 */
@Entity
@Table(name = "adress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adress.findAll", query = "SELECT a FROM Adress a"),
    @NamedQuery(name = "Adress.findByAdressID", query = "SELECT a FROM Adress a WHERE a.adressID = :adressID"),
    @NamedQuery(name = "Adress.findByStreet", query = "SELECT a FROM Adress a WHERE a.street = :street"),
    @NamedQuery(name = "Adress.findByHousenumber", query = "SELECT a FROM Adress a WHERE a.housenumber = :housenumber"),
    @NamedQuery(name = "Adress.findByCity", query = "SELECT a FROM Adress a WHERE a.city = :city"),
    @NamedQuery(name = "Adress.findByCountry", query = "SELECT a FROM Adress a WHERE a.country = :country"),
    @NamedQuery(name = "Adress.findByRegion", query = "SELECT a FROM Adress a WHERE a.region = :region"),
    @NamedQuery(name = "Adress.findByPostalCode", query = "SELECT a FROM Adress a WHERE a.postalCode = :postalCode"),
    @NamedQuery(name = "Adress.findByIsShippingAdress", query = "SELECT a FROM Adress a WHERE a.isShippingAdress = :isShippingAdress"),
    @NamedQuery(name = "Adress.findByIsInvoiceAddress", query = "SELECT a FROM Adress a WHERE a.isInvoiceAddress = :isInvoiceAddress")})
public class Adress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "adressID")
    private Integer adressID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "housenumber")
    private String housenumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "country")
    private String country;
    @Size(max = 45)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @NotNull
    @Column(name = "postalCode")
    private int postalCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isShippingAdress")
    private boolean isShippingAdress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isInvoiceAddress")
    private boolean isInvoiceAddress;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userIduser;

    public Adress() {
    }

    public Adress(Integer adressID) {
        this.adressID = adressID;
    }

    public Adress(Integer adressID, String street, String housenumber, String city, String country, int postalCode, boolean isShippingAdress, boolean isInvoiceAddress) {
        this.adressID = adressID;
        this.street = street;
        this.housenumber = housenumber;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.isShippingAdress = isShippingAdress;
        this.isInvoiceAddress = isInvoiceAddress;
    }

    public Integer getAdressID() {
        return adressID;
    }

    public void setAdressID(Integer adressID) {
        this.adressID = adressID;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
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

    public User getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(User userIduser) {
        this.userIduser = userIduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adressID != null ? adressID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adress)) {
            return false;
        }
        Adress other = (Adress) object;
        if ((this.adressID == null && other.adressID != null) || (this.adressID != null && !this.adressID.equals(other.adressID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Adress[ adressID=" + adressID + " ]";
    }
    
}
