package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Buchungs Entität
 */
@Entity
@Table(name = "rent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rent.findAll", query = "SELECT r FROM Rent r"),
    @NamedQuery(name = "Rent.findByRentId", query = "SELECT r FROM Rent r WHERE r.rentId = :rentId"),
    @NamedQuery(name = "Rent.findByUser", query = "SELECT r FROM Rent r WHERE r.useruserId = :useruserId"),
    @NamedQuery(name = "Rent.findActiveRents", query = "SELECT r FROM Rent r WHERE r.useruserId = :useruserId AND (:date BETWEEN r.startdate AND r.enddate OR :date < r.startdate)"),
    @NamedQuery(name = "Rent.findByTotalPrice", query = "SELECT r FROM Rent r WHERE r.totalPrice = :totalPrice"),
    @NamedQuery(name = "Rent.findByLength", query = "SELECT r FROM Rent r WHERE r.length = :length"),
    @NamedQuery(name = "Rent.findByStartdate", query = "SELECT r FROM Rent r WHERE r.startdate = :startdate"),
    @NamedQuery(name = "Rent.findByEnddate", query = "SELECT r FROM Rent r WHERE r.enddate = :enddate")})

public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rentId")
    private Integer rentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalPrice")
    private double totalPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "length")
    private int length;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private User useruserId;
    @JoinColumn(name = "car_modelId", referencedColumnName = "carId")
    @ManyToOne(optional = false)
    private Car carmodelId;

    public Rent() {
    }

    public Rent(Date startdate, Date enddate) {
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public User getUseruserId() {
        return useruserId;
    }

    public void setUseruserId(User useruserId) {
        this.useruserId = useruserId;
    }

    public Car getCarmodelId() {
        return carmodelId;
    }

    public void setCarmodelId(Car carmodelId) {
        this.carmodelId = carmodelId;
    }
}
