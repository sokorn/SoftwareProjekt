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

@Entity
@Table(name = "car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByTourque", query = "SELECT c FROM Car c WHERE c.tourque = :tourque"),
    @NamedQuery(name = "Car.findByCcm", query = "SELECT c FROM Car c WHERE c.ccm = :ccm"),
    @NamedQuery(name = "Car.findByAcceleration", query = "SELECT c FROM Car c WHERE c.acceleration = :acceleration"),
    @NamedQuery(name = "Car.findByPower", query = "SELECT c FROM Car c WHERE c.power = :power"),
    @NamedQuery(name = "Car.findByMaxSpeed", query = "SELECT c FROM Car c WHERE c.maxSpeed = :maxSpeed"),
    @NamedQuery(name = "Car.findByWeight", query = "SELECT c FROM Car c WHERE c.weight = :weight"),
    @NamedQuery(name = "Car.findByPrice", query = "SELECT c FROM Car c WHERE c.price = :price"),
    @NamedQuery(name = "Car.findByTravellers", query = "SELECT c FROM Car c WHERE c.travellers = :travellers"),
    @NamedQuery(name = "Car.findByMinAge", query = "SELECT c FROM Car c WHERE c.minAge = :minAge"),
    @NamedQuery(name = "Car.findByModelname", query = "SELECT c FROM Car c WHERE c.modelname = :modelname"),
    @NamedQuery(name = "Car.findByBrandname", query = "SELECT c FROM Car c WHERE c.brandname = :brandname")})
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carId")
    private Integer carId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "build")
    private int build;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carmodelId")
    private Collection<Rent> rentCollection;

    public int getBuild() {
        return build;
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

    @XmlTransient
    public Collection<Rent> getRentCollection() {
        return rentCollection;
    }

    public void setRentCollection(Collection<Rent> rentCollection) {
        this.rentCollection = rentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Car[ carId=" + carId + " ]";
    }

}
