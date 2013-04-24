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
@Table(name = "Konto", catalog = "BankDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konto.findAll", query = "SELECT k FROM Konto k"),
    @NamedQuery(name = "Konto.findById", query = "SELECT k FROM Konto k WHERE k.id = :id"),
    @NamedQuery(name = "Konto.findByPersonid", query = "SELECT k FROM Konto k WHERE k.personid = :personid"),
    @NamedQuery(name = "Konto.findByBankid", query = "SELECT k FROM Konto k WHERE k.bankid = :bankid"),
    @NamedQuery(name = "Konto.findByKontotype", query = "SELECT k FROM Konto k WHERE k.kontotype = :kontotype"),
    @NamedQuery(name = "Konto.findByKontonr", query = "SELECT k FROM Konto k WHERE k.kontonr = :kontonr"),
    @NamedQuery(name = "Konto.findBySisteEndringsTidspunkt", query = "SELECT k FROM Konto k WHERE k.sisteEndringsTidspunkt = :sisteEndringsTidspunkt"),
    @NamedQuery(name = "Konto.findBySaldo", query = "SELECT k FROM Konto k WHERE k.saldo = :saldo")})
public class Konto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Person_id")
    private int personid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "Bankid")
    private String bankid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Kontotype")
    private String kontotype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Kontonr")
    private String kontonr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SisteEndringsTidspunkt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sisteEndringsTidspunkt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Saldo")
    private double saldo;

    /**
     *
     */
    public Konto() {
    }

    /**
     *
     * @param id
     */
    public Konto(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param personid
     * @param bankid
     * @param kontotype
     * @param kontonr
     * @param sisteEndringsTidspunkt
     * @param saldo
     */
    public Konto(Integer id, int personid, String bankid, String kontotype, String kontonr, Date sisteEndringsTidspunkt, double saldo) {
        this.id = id;
        this.personid = personid;
        this.bankid = bankid;
        this.kontotype = kontotype;
        this.kontonr = kontonr;
        this.sisteEndringsTidspunkt = sisteEndringsTidspunkt;
        this.saldo = saldo;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getPersonid() {
        return personid;
    }

    /**
     *
     * @param personid
     */
    public void setPersonid(int personid) {
        this.personid = personid;
    }

    /**
     *
     * @return
     */
    public String getBankid() {
        return bankid;
    }

    /**
     *
     * @param bankid
     */
    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    /**
     *
     * @return
     */
    public String getKontotype() {
        return kontotype;
    }

    /**
     *
     * @param kontotype
     */
    public void setKontotype(String kontotype) {
        this.kontotype = kontotype;
    }

    /**
     *
     * @return
     */
    public String getKontonr() {
        return kontonr;
    }

    /**
     *
     * @param kontonr
     */
    public void setKontonr(String kontonr) {
        this.kontonr = kontonr;
    }

    /**
     *
     * @return
     */
    public Date getSisteEndringsTidspunkt() {
        return sisteEndringsTidspunkt;
    }

    /**
     *
     * @param sisteEndringsTidspunkt
     */
    public void setSisteEndringsTidspunkt(Date sisteEndringsTidspunkt) {
        this.sisteEndringsTidspunkt = sisteEndringsTidspunkt;
    }

    /**
     *
     * @return
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     *
     * @param saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
        if (!(object instanceof Konto)) {
            return false;
        }
        Konto other = (Konto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "no.hin.stg.intapp.oblig4web.entities.Konto[ id=" + id + " ]";
    }
}
