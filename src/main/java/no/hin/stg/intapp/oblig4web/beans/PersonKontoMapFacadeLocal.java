/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.beans;

import java.util.List;
import javax.ejb.Local;
import no.hin.stg.intapp.oblig4web.entities.PersonKontoMap;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Local
public interface PersonKontoMapFacadeLocal {

    /**
     *
     * @param personKontoMap
     */
    void create(PersonKontoMap personKontoMap);

    /**
     *
     * @param personKontoMap
     */
    void edit(PersonKontoMap personKontoMap);

    /**
     *
     * @param personKontoMap
     */
    void remove(PersonKontoMap personKontoMap);

    /**
     *
     * @param id
     * @return
     */
    PersonKontoMap find(Object id);

    /**
     *
     * @return
     */
    List<PersonKontoMap> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<PersonKontoMap> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
}
