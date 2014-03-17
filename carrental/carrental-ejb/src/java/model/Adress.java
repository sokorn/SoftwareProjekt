package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "adress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adress.findAll", query = "SELECT a FROM Adress a"),
    @NamedQuery(name = "Adress.findByAdressId", query = "SELECT a FROM Adress a WHERE a.adressId = :adressId"),
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adressId")
    private Integer adressId;
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
    @Size(min = 1, max = 45)
    @Column(name = "postalCode")
    private String postalCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isShippingAdress")
    private boolean isShippingAdress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isInvoiceAddress")
    private boolean isInvoiceAddress;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private User useruserId;

    

    public Adress() {
    }

    public Adress(Integer adressId) {
        this.adressId = adressId;
    }

    public Adress(String street, String housenumber, String city, String country, String postalCode, boolean isShippingAdress, boolean isInvoiceAddress) {
        this.street = street;
        this.housenumber = housenumber;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.isShippingAdress = isShippingAdress;
        this.isInvoiceAddress = isInvoiceAddress;
    }

    public Integer getAdressId() {
        return adressId;
    }

    public void setAdressId(Integer adressId) {
        this.adressId = adressId;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adressId != null ? adressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adress)) {
            return false;
        }
        Adress other = (Adress) object;
        if ((this.adressId == null && other.adressId != null) || (this.adressId != null && !this.adressId.equals(other.adressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Adress[ adressId=" + adressId + " ]";
    }

}
