/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.beans;

import java.util.List;
import javax.ejb.Local;
import no.hin.stg.intapp.oblig4web.entities.KontoTransaksjon;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Local
public interface KontoTransaksjonFacadeLocal {

    /**
     *
     * @param kontoTransaksjon
     */
    void create(KontoTransaksjon kontoTransaksjon);

    /**
     *
     * @param kontoTransaksjon
     */
    void edit(KontoTransaksjon kontoTransaksjon);

    /**
     *
     * @param kontoTransaksjon
     */
    void remove(KontoTransaksjon kontoTransaksjon);

    /**
     *
     * @param id
     * @return
     */
    KontoTransaksjon find(Object id);

    /**
     *
     * @return
     */
    List<KontoTransaksjon> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<KontoTransaksjon> findRange(int[] range);

    /**
     *
     * @param accNmbr
     * @return
     */
    List<KontoTransaksjon> findByAccNmbr(int accNmbr);

    /**
     *
     * @return
     */
    int count();
}
