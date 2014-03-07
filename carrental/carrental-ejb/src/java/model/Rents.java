/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Marco
 */
@Entity
@Table(name = "rents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rents.findAll", query = "SELECT r FROM Rents r"),
    @NamedQuery(name = "Rents.findByRentID", query = "SELECT r FROM Rents r WHERE r.rentID = :rentID"),
    @NamedQuery(name = "Rents.findByTotalPrice", query = "SELECT r FROM Rents r WHERE r.totalPrice = :totalPrice"),
    @NamedQuery(name = "Rents.findByLength", query = "SELECT r FROM Rents r WHERE r.length = :length"),
    @NamedQuery(name = "Rents.findByStartdate", query = "SELECT r FROM Rents r WHERE r.startdate = :startdate"),
    @NamedQuery(name = "Rents.findByEnddate", query = "SELECT r FROM Rents r WHERE r.enddate = :enddate")})
public class Rents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rentID")
    private Integer rentID;
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
    @JoinColumn(name = "car_carId", referencedColumnName = "carId")
    @ManyToOne(optional = false)
    private Car carcarId;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userIduser;

    public Rents() {
    }

    public Rents(Integer rentID) {
        this.rentID = rentID;
    }

    public Rents(Integer rentID, double totalPrice, int length, Date startdate, Date enddate) {
        this.rentID = rentID;
        this.totalPrice = totalPrice;
        this.length = length;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Integer getRentID() {
        return rentID;
    }

    public void setRentID(Integer rentID) {
        this.rentID = rentID;
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

    public Car getCarcarId() {
        return carcarId;
    }

    public void setCarcarId(Car carcarId) {
        this.carcarId = carcarId;
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
        hash += (rentID != null ? rentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rents)) {
            return false;
        }
        Rents other = (Rents) object;
        if ((this.rentID == null && other.rentID != null) || (this.rentID != null && !this.rentID.equals(other.rentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Rents[ rentID=" + rentID + " ]";
    }
    
}
