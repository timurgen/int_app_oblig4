/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.beans;

import java.util.List;
import javax.ejb.Local;
import no.hin.stg.intapp.oblig4web.entities.Person;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
@Local
public interface PersonFacadeLocal {

    /**
     *
     * @param person
     */
    void create(Person person);

    /**
     *
     * @param person
     */
    void edit(Person person);

    /**
     *
     * @param person
     */
    void remove(Person person);

    /**
     *
     * @param id
     * @return
     */
    Person find(Object id);

    /**
     *
     * @return
     */
    List<Person> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Person> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
}
