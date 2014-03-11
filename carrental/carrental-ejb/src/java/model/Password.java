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
@Table(name = "password")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Password.findAll", query = "SELECT p FROM Password p"),
    @NamedQuery(name = "Password.findByPasswordhash", query = "SELECT p FROM Password p WHERE p.passwordhash = :passwordhash"),
    @NamedQuery(name = "Password.findByPasswordId", query = "SELECT p FROM Password p WHERE p.passwordId = :passwordId")})
public class Password implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "passwordhash")
    private String passwordhash;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "passwordId")
    private String passwordId;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private User useruserId;

    public Password() {
    }

    public Password(String passwordId) {
        this.passwordId = passwordId;
    }

    public Password(String passwordId, String passwordhash) {
        this.passwordId = passwordId;
        this.passwordhash = passwordhash;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(String passwordId) {
        this.passwordId = passwordId;
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
        hash += (passwordId != null ? passwordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Password)) {
            return false;
        }
        Password other = (Password) object;
        if ((this.passwordId == null && other.passwordId != null) || (this.passwordId != null && !this.passwordId.equals(other.passwordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Password[ passwordId=" + passwordId + " ]";
    }
    
}
