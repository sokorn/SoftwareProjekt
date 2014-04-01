package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Auto Entität
 */
@Entity
@Table(name = "car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByCarId", query = "SELECT c FROM Car c WHERE c.carId = :carId"),
    @NamedQuery(name = "Car.findByModelname", query = "SELECT c FROM Car c WHERE c.modelname = :modelname"),
    @NamedQuery(name = "Car.findByBrandname", query = "SELECT c FROM Car c WHERE c.brandname = :brandname"),
    @NamedQuery(name = "Car.getBrandList", query = "SELECT distinct c.brandname FROM Car c ORDER BY c.brandname"),
    @NamedQuery(name = "Car.getModelList", query = "SELECT distinct c.modelname FROM Car c ORDER BY c.modelname"),
    @NamedQuery(name = "Car.getModelnameByBrandname", query = "SELECT distinct c.modelname FROM Car c WHERE c.brandname = :brandname")})

public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carId")
    private Integer carId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tourque")
    private int tourque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ccm")
    private double ccm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acceleration")
    private double acceleration;
    @Basic(optional = false)
    @NotNull
    @Column(name = "power")
    private int power;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maxSpeed")
    private int maxSpeed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private int weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "travellers")
    private int travellers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minAge")
    private int minAge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "modelname")
    private String modelname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "brandname")
    private String brandname;
    @Size(max = 45)
    @Column(name = "modelpicture")
    private String modelpicture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carmodelId")
    private Collection<Rent> rentCollection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "available")
    private boolean available;

    public Car() {
    }

    public Integer getCarId() {
        return carId;
    }

    public int getTourque() {
        return tourque;
    }

    public double getCcm() {
        return ccm;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public int getPower() {
        return power;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int getTravellers() {
        return travellers;
    }

    public int getMinAge() {
        return minAge;
    }

    public String getModelname() {
        return modelname;
    }

    public String getBrandname() {
        return brandname;
    }

    public String getModelpicture() {
        return modelpicture;
    }

    @XmlTransient
    public Collection<Rent> getRentCollection() {
        return rentCollection;
    }

    // fügt dem Benutzer eine Buchung hinzu
    public void addRent(Rent rent) {
        rentCollection.add(rent);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
