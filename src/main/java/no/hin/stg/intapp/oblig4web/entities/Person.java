/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.entities;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Entity
@Stateless
@Table(name = "Person", catalog = "BankDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByFodselsdato", query = "SELECT p FROM Person p WHERE p.fodselsdato = :fodselsdato"),
    @NamedQuery(name = "Person.findByFodselsnummer", query = "SELECT p FROM Person p WHERE p.fodselsnummer = :fodselsnummer"),
    @NamedQuery(name = "Person.findByNavn", query = "SELECT p FROM Person p WHERE p.navn = :navn"),
    @NamedQuery(name = "Person.findByAdresselinje1", query = "SELECT p FROM Person p WHERE p.adresselinje1 = :adresselinje1"),
    @NamedQuery(name = "Person.findByAdresselinje2", query = "SELECT p FROM Person p WHERE p.adresselinje2 = :adresselinje2"),
    @NamedQuery(name = "Person.findByPostnummer", query = "SELECT p FROM Person p WHERE p.postnummer = :postnummer"),
    @NamedQuery(name = "Person.findByPassord", query = "SELECT p FROM Person p WHERE p.passord = :passord"),
    @NamedQuery(name = "Person.findByPINkode", query = "SELECT p FROM Person p WHERE p.pINkode = :pINkode"),
    @NamedQuery(name = "Person.findByPoststed", query = "SELECT p FROM Person p WHERE p.poststed = :poststed"),
    @NamedQuery(name = "Person.findByKommentar", query = "SELECT p FROM Person p WHERE p.kommentar = :kommentar"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fodselsdato")
    @Temporal(TemporalType.DATE)
    private Date fodselsdato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Fodselsnummer")
    private String fodselsnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Navn")
    private String navn;
    @Size(max = 30)
    @Column(name = "Adresse_linje_1")
    private String adresselinje1;
    @Size(max = 30)
    @Column(name = "Adresse_linje_2")
    private String adresselinje2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "Postnummer")
    private String postnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Passord")
    private String passord;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PIN_kode")
    private int pINkode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "Poststed")
    private String poststed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Kommentar")
    private String kommentar;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Person(Integer id, Date fodselsdato, String fodselsnummer, String navn, String postnummer, String passord, int pINkode, String poststed, String kommentar) {
        this.id = id;
        this.fodselsdato = fodselsdato;
        this.fodselsnummer = fodselsnummer;
        this.navn = navn;
        this.postnummer = postnummer;
        this.passord = passord;
        this.pINkode = pINkode;
        this.poststed = poststed;
        this.kommentar = kommentar;
    }

    public Date getFodselsdato() {
        return fodselsdato;
    }

    public void setFodselsdato(Date fodselsdato) {
        this.fodselsdato = fodselsdato;
    }

    public String getFodselsnummer() {
        return fodselsnummer;
    }

    public void setFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresselinje1() {
        return adresselinje1;
    }

    public void setAdresselinje1(String adresselinje1) {
        this.adresselinje1 = adresselinje1;
    }

    public String getAdresselinje2() {
        return adresselinje2;
    }

    public void setAdresselinje2(String adresselinje2) {
        this.adresselinje2 = adresselinje2;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public int getPINkode() {
        return pINkode;
    }

    public void setPINkode(int pINkode) {
        this.pINkode = pINkode;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "no.hin.stg.intapp.oblig4web.entities.Person[ id=" + id + " ]";
    }
}
