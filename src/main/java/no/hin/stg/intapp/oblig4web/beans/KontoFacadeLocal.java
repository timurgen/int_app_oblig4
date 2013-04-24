/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.beans;

import java.util.List;
import javax.ejb.Local;
import no.hin.stg.intapp.oblig4web.entities.Konto;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Local
public interface KontoFacadeLocal {

    /**
     *
     * @param konto
     */
    void create(Konto konto);

    /**
     *
     * @param konto
     */
    void edit(Konto konto);

    /**
     *
     * @param konto
     */
    void remove(Konto konto);

    /**
     *
     * @param id
     * @return
     */
    Konto find(Object id);

    /**
     *
     * @return
     */
    List<Konto> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Konto> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
}
