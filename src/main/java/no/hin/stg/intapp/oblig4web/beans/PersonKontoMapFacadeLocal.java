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

    void create(PersonKontoMap personKontoMap);

    void edit(PersonKontoMap personKontoMap);

    void remove(PersonKontoMap personKontoMap);

    PersonKontoMap find(Object id);

    List<PersonKontoMap> findAll();

    List<PersonKontoMap> findRange(int[] range);

    int count();
    
}
