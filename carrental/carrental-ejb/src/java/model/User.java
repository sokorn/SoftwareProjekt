package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@NamedQueries(
        {
            @NamedQuery(name = "User.login",
                    query = "SELECT u from User u WHERE u.mail = :login")
        })

public class User implements Serializable
{

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
    private List<Adress> adressList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useruserId")
    private List<Rent> rentList;

    /**
     * Parameterloser Konstruktor. Muss vorhanden sein, sonst gibt es einen
     * Fehler.
     */
    public User()
    {
    }

    /**
     * Konstruktor für ein Benutzerobjekt.
     *
     * @param title Titel
     * @param firstname Vorname
     * @param lastname Nachname
     * @param birthdate Geburtstag
     * @param mail Mailadresse
     * @param passwordHash Passworthash
     */
    public User(String title, String firstname, String lastname,
            Date birthdate, String mail, String passwordHash)
    {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.mail = mail;
        this.passwordhash = passwordHash;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public Date getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getPasswordhash()
    {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash)
    {
        this.passwordhash = passwordhash;
    }

    @XmlTransient
    public List<Adress> getAdressList()
    {
        return adressList;
    }

    // fügt dem Benutzer eine Adresse hinzu
    public void addAdress(Adress adress)
    {
        adressList.add(adress);
    }

    @XmlTransient
    public List<Rent> getRentList()
    {
        return rentList;
    }

    // fügt dem Benutzer eine Buchung hinzu
    public void addRent(Rent rent)
    {
        rentList.add(rent);
    }
}
