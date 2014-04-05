package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Benutzer Entität
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "User.login", query = "SELECT u from User u WHERE u.mail = :login")})

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String mail;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    private String title;
    private String firstname;
    private String lastname;
    private String passwordhash;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useruserId")
    private Collection<Adress> adressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useruserId")
    private Collection<Rent> rentCollection;

    public User() {
    }

    public User(String title, String firstname, String lastname, Date birthdate, String mail, String passwordHash) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.mail = mail;
        this.passwordhash = passwordHash;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    @XmlTransient
    public Collection<Adress> getAdressCollection() {
        return adressCollection;
    }

    // fügt dem Benutzer eine Adresse hinzu
    public void addAdress(Adress adress) {
        adressCollection.add(adress);
    }

    @XmlTransient
    public Collection<Rent> getRentCollection() {
        return rentCollection;
    }

    // fügt dem Benutzer eine Buchung hinzu
    public void addRent(Rent rent) {
        rentCollection.add(rent);
    }
}
