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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Auto Entität
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByCarId", query = "SELECT c FROM Car c WHERE c.carId = :carId"),
    @NamedQuery(name = "Car.findByModelname", query = "SELECT c FROM Car c WHERE c.modelname = :modelname"),
    @NamedQuery(name = "Car.findByBrandname", query = "SELECT c FROM Car c WHERE c.brandname = :brandname"),
    @NamedQuery(name = "Car.getBrandList", query = "SELECT distinct c.brandname FROM Car c ORDER BY c.brandname"),
    @NamedQuery(name = "Car.getModelList", query = "SELECT distinct c.modelname FROM Car c ORDER BY c.modelname"),
    @NamedQuery(name = "Car.getModelnameByBrandname", query = "SELECT distinct c.modelname FROM Car c WHERE c.brandname = :brandname")})

public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private int tourque;
    private double ccm;
    private double acceleration;
    private int power;
    private int maxSpeed;
    private int weight;
    private double price;
    private int travellers;
    private int minAge;
    private String modelname;
    private String brandname;
    private String modelpicture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carmodelId")
    private Collection<Rent> rentCollection;
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
