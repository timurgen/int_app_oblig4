/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.beans;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import no.hin.stg.intapp.oblig4web.entities.Konto;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Stateless
@Singleton
public class KontoFacade extends AbstractFacade<Konto> implements KontoFacadeLocal {

    @PersistenceContext(unitName = "no.hin.stg.intapp_oblig4web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public KontoFacade() {
        super(Konto.class);
    }
}
