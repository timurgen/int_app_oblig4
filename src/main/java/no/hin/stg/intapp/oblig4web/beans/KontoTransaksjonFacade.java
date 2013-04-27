/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import no.hin.stg.intapp.oblig4web.entities.KontoTransaksjon;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Stateless
@Singleton
public class KontoTransaksjonFacade extends AbstractFacade<KontoTransaksjon> implements KontoTransaksjonFacadeLocal {

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
    public KontoTransaksjonFacade() {
        super(KontoTransaksjon.class);
    }

    /**
     *
     * @param accNmbr
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<KontoTransaksjon> findByAccNmbr(int accNmbr) {
        Query trByAccNmbr = getEntityManager().createNamedQuery("KontoTransaksjon.findByFraKonto");
        trByAccNmbr.setParameter("fraKonto", accNmbr);
        List<KontoTransaksjon> fraTr = trByAccNmbr.getResultList();
        trByAccNmbr = getEntityManager().createNamedQuery("KontoTransaksjon.findByTilKonto");
        trByAccNmbr.setParameter("tilKonto", accNmbr);
        List<KontoTransaksjon> tilTr = trByAccNmbr.getResultList();
        List<KontoTransaksjon> result = new ArrayList<>(fraTr);
        result.addAll(tilTr);
        return result;
    }
}
