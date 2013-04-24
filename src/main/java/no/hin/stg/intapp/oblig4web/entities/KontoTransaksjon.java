/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "KontoTransaksjon", catalog = "BankDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KontoTransaksjon.findAll", query = "SELECT k FROM KontoTransaksjon k"),
    @NamedQuery(name = "KontoTransaksjon.findById", query = "SELECT k FROM KontoTransaksjon k WHERE k.id = :id"),
    @NamedQuery(name = "KontoTransaksjon.findByRegistreringsTidspunkt", query = "SELECT k FROM KontoTransaksjon k WHERE k.registreringsTidspunkt = :registreringsTidspunkt"),
    @NamedQuery(name = "KontoTransaksjon.findByTransaksjonsTid", query = "SELECT k FROM KontoTransaksjon k WHERE k.transaksjonsTid = :transaksjonsTid"),
    @NamedQuery(name = "KontoTransaksjon.findByFraKonto", query = "SELECT k FROM KontoTransaksjon k WHERE k.fraKonto = :fraKonto"),
    @NamedQuery(name = "KontoTransaksjon.findByTilKonto", query = "SELECT k FROM KontoTransaksjon k WHERE k.tilKonto = :tilKonto"),
    @NamedQuery(name = "KontoTransaksjon.findByTranskasjonsbeskrivelse", query = "SELECT k FROM KontoTransaksjon k WHERE k.transkasjonsbeskrivelse = :transkasjonsbeskrivelse"),
    @NamedQuery(name = "KontoTransaksjon.findByTransType", query = "SELECT k FROM KontoTransaksjon k WHERE k.transType = :transType"),
    @NamedQuery(name = "KontoTransaksjon.findByInitsiator", query = "SELECT k FROM KontoTransaksjon k WHERE k.initsiator = :initsiator"),
    @NamedQuery(name = "KontoTransaksjon.findByBelop", query = "SELECT k FROM KontoTransaksjon k WHERE k.belop = :belop")})
public class KontoTransaksjon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RegistreringsTidspunkt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registreringsTidspunkt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TransaksjonsTid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transaksjonsTid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FraKonto")
    private int fraKonto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TilKonto")
    private int tilKonto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Transkasjonsbeskrivelse")
    private String transkasjonsbeskrivelse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TransType")
    private int transType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Initsiator")
    private int initsiator;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Belop")
    private double belop;

    public KontoTransaksjon() {
    }

    public KontoTransaksjon(Integer id) {
        this.id = id;
    }

    public KontoTransaksjon(Integer id, Date registreringsTidspunkt, Date transaksjonsTid, int fraKonto, int tilKonto, String transkasjonsbeskrivelse, int transType, int initsiator, double belop) {
        this.id = id;
        this.registreringsTidspunkt = registreringsTidspunkt;
        this.transaksjonsTid = transaksjonsTid;
        this.fraKonto = fraKonto;
        this.tilKonto = tilKonto;
        this.transkasjonsbeskrivelse = transkasjonsbeskrivelse;
        this.transType = transType;
        this.initsiator = initsiator;
        this.belop = belop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegistreringsTidspunkt() {
        return registreringsTidspunkt;
    }

    public void setRegistreringsTidspunkt(Date registreringsTidspunkt) {
        this.registreringsTidspunkt = registreringsTidspunkt;
    }

    public Date getTransaksjonsTid() {
        return transaksjonsTid;
    }

    public void setTransaksjonsTid(Date transaksjonsTid) {
        this.transaksjonsTid = transaksjonsTid;
    }

    public int getFraKonto() {
        return fraKonto;
    }

    public void setFraKonto(int fraKonto) {
        this.fraKonto = fraKonto;
    }

    public int getTilKonto() {
        return tilKonto;
    }

    public void setTilKonto(int tilKonto) {
        this.tilKonto = tilKonto;
    }

    public String getTranskasjonsbeskrivelse() {
        return transkasjonsbeskrivelse;
    }

    public void setTranskasjonsbeskrivelse(String transkasjonsbeskrivelse) {
        this.transkasjonsbeskrivelse = transkasjonsbeskrivelse;
    }

    public int getTransType() {
        return transType;
    }

    public void setTransType(int transType) {
        this.transType = transType;
    }

    public int getInitsiator() {
        return initsiator;
    }

    public void setInitsiator(int initsiator) {
        this.initsiator = initsiator;
    }

    public double getBelop() {
        return belop;
    }

    public void setBelop(double belop) {
        this.belop = belop;
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
        if (!(object instanceof KontoTransaksjon)) {
            return false;
        }
        KontoTransaksjon other = (KontoTransaksjon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "no.hin.stg.intapp.oblig4web.entities.KontoTransaksjon[ id=" + id + " ]";
    }
    
}
