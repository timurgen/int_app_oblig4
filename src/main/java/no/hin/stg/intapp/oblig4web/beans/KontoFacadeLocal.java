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

    void create(Konto konto);

    void edit(Konto konto);

    void remove(Konto konto);

    Konto find(Object id);

    List<Konto> findAll();

    List<Konto> findRange(int[] range);

    int count();
    
}
