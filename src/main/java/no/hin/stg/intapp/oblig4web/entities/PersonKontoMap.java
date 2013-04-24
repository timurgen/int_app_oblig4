/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Entity
@Table(name = "PersonKontoMap", catalog = "BankDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonKontoMap.findAll", query = "SELECT p FROM PersonKontoMap p"),
    @NamedQuery(name = "PersonKontoMap.findByPersonid", query = "SELECT p FROM PersonKontoMap p WHERE p.personKontoMapPK.personid = :personid"),
    @NamedQuery(name = "PersonKontoMap.findByKontoid", query = "SELECT p FROM PersonKontoMap p WHERE p.personKontoMapPK.kontoid = :kontoid"),
    @NamedQuery(name = "PersonKontoMap.findByGyldigFraOgMed", query = "SELECT p FROM PersonKontoMap p WHERE p.gyldigFraOgMed = :gyldigFraOgMed"),
    @NamedQuery(name = "PersonKontoMap.findByGyldigTilOgMed", query = "SELECT p FROM PersonKontoMap p WHERE p.gyldigTilOgMed = :gyldigTilOgMed")})
public class PersonKontoMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonKontoMapPK personKontoMapPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GyldigFraOgMed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gyldigFraOgMed;
    @Column(name = "GyldigTilOgMed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gyldigTilOgMed;

    public PersonKontoMap() {
    }

    public PersonKontoMap(PersonKontoMapPK personKontoMapPK) {
        this.personKontoMapPK = personKontoMapPK;
    }

    public PersonKontoMap(PersonKontoMapPK personKontoMapPK, Date gyldigFraOgMed) {
        this.personKontoMapPK = personKontoMapPK;
        this.gyldigFraOgMed = gyldigFraOgMed;
    }

    public PersonKontoMap(int personid, int kontoid) {
        this.personKontoMapPK = new PersonKontoMapPK(personid, kontoid);
    }

    public PersonKontoMapPK getPersonKontoMapPK() {
        return personKontoMapPK;
    }

    public void setPersonKontoMapPK(PersonKontoMapPK personKontoMapPK) {
        this.personKontoMapPK = personKontoMapPK;
    }

    public Date getGyldigFraOgMed() {
        return gyldigFraOgMed;
    }

    public void setGyldigFraOgMed(Date gyldigFraOgMed) {
        this.gyldigFraOgMed = gyldigFraOgMed;
    }

    public Date getGyldigTilOgMed() {
        return gyldigTilOgMed;
    }

    public void setGyldigTilOgMed(Date gyldigTilOgMed) {
        this.gyldigTilOgMed = gyldigTilOgMed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personKontoMapPK != null ? personKontoMapPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonKontoMap)) {
            return false;
        }
        PersonKontoMap other = (PersonKontoMap) object;
        if ((this.personKontoMapPK == null && other.personKontoMapPK != null) || (this.personKontoMapPK != null && !this.personKontoMapPK.equals(other.personKontoMapPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "no.hin.stg.intapp.oblig4web.entities.PersonKontoMap[ personKontoMapPK=" + personKontoMapPK + " ]";
    }
}
