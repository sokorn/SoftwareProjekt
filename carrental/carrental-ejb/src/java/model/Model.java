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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marco
 */
@Entity
@Table(name = "model")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Model.findAll", query = "SELECT m FROM Model m"),
    @NamedQuery(name = "Model.findByModelId", query = "SELECT m FROM Model m WHERE m.modelId = :modelId"),
    @NamedQuery(name = "Model.findByModelname", query = "SELECT m FROM Model m WHERE m.modelname = :modelname"),
    @NamedQuery(name = "Model.findByBuild", query = "SELECT m FROM Model m WHERE m.build = :build"),
    @NamedQuery(name = "Model.findByTourque", query = "SELECT m FROM Model m WHERE m.tourque = :tourque"),
    @NamedQuery(name = "Model.findByCcm", query = "SELECT m FROM Model m WHERE m.ccm = :ccm"),
    @NamedQuery(name = "Model.findByAcceleration", query = "SELECT m FROM Model m WHERE m.acceleration = :acceleration"),
    @NamedQuery(name = "Model.findByPower", query = "SELECT m FROM Model m WHERE m.power = :power"),
    @NamedQuery(name = "Model.findByMaxSpeed", query = "SELECT m FROM Model m WHERE m.maxSpeed = :maxSpeed"),
    @NamedQuery(name = "Model.findByWeight", query = "SELECT m FROM Model m WHERE m.weight = :weight"),
    @NamedQuery(name = "Model.findByPrice", query = "SELECT m FROM Model m WHERE m.price = :price"),
    @NamedQuery(name = "Model.findByTravellers", query = "SELECT m FROM Model m WHERE m.travellers = :travellers"),
    @NamedQuery(name = "Model.findByMinAge", query = "SELECT m FROM Model m WHERE m.minAge = :minAge")})
public class Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "modelId")
    private Integer modelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "modelname")
    private String modelname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "build")
    @Temporal(TemporalType.TIMESTAMP)
    private Date build;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tourque")
    private int tourque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ccm")
    private int ccm;
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
    @JoinColumn(name = "brand_brandId", referencedColumnName = "brandId")
    @ManyToOne(optional = false)
    private Brand brandbrandId;

    public Model() {
    }

    public Model(Integer modelId) {
        this.modelId = modelId;
    }

    public Model(Integer modelId, String modelname, Date build, int tourque, int ccm, double acceleration, int power, int maxSpeed, int weight, double price, int travellers, int minAge) {
        this.modelId = modelId;
        this.modelname = modelname;
        this.build = build;
        this.tourque = tourque;
        this.ccm = ccm;
        this.acceleration = acceleration;
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.price = price;
        this.travellers = travellers;
        this.minAge = minAge;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public Date getBuild() {
        return build;
    }

    public void setBuild(Date build) {
        this.build = build;
    }

    public int getTourque() {
        return tourque;
    }

    public void setTourque(int tourque) {
        this.tourque = tourque;
    }

    public int getCcm() {
        return ccm;
    }

    public void setCcm(int ccm) {
        this.ccm = ccm;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTravellers() {
        return travellers;
    }

    public void setTravellers(int travellers) {
        this.travellers = travellers;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public Brand getBrandbrandId() {
        return brandbrandId;
    }

    public void setBrandbrandId(Brand brandbrandId) {
        this.brandbrandId = brandbrandId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelId != null ? modelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Model)) {
            return false;
        }
        Model other = (Model) object;
        if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Model[ modelId=" + modelId + " ]";
    }
    
}
