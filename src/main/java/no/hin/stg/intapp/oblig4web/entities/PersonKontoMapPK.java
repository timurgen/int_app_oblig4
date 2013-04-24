/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Embeddable
public class PersonKontoMapPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Person_id")
    private int personid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Konto_id")
    private int kontoid;

    public PersonKontoMapPK() {
    }

    public PersonKontoMapPK(int personid, int kontoid) {
        this.personid = personid;
        this.kontoid = kontoid;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public int getKontoid() {
        return kontoid;
    }

    public void setKontoid(int kontoid) {
        this.kontoid = kontoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) personid;
        hash += (int) kontoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonKontoMapPK)) {
            return false;
        }
        PersonKontoMapPK other = (PersonKontoMapPK) object;
        if (this.personid != other.personid) {
            return false;
        }
        if (this.kontoid != other.kontoid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "no.hin.stg.intapp.oblig4web.entities.PersonKontoMapPK[ personid=" + personid + ", kontoid=" + kontoid + " ]";
    }
    
}
