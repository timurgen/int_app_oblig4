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

    void create(KontoTransaksjon kontoTransaksjon);

    void edit(KontoTransaksjon kontoTransaksjon);

    void remove(KontoTransaksjon kontoTransaksjon);

    KontoTransaksjon find(Object id);

    List<KontoTransaksjon> findAll();

    List<KontoTransaksjon> findRange(int[] range);

    int count();
    
}
