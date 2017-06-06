/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service;

import hr.diskobolos.model.IIdentifier;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Interface that provides main CRUD JPA methods
 *
 * @author Tomislav Čavka
 *
 * @param <T> the type of object this interface provides
 * @param <Id> the type of Id this interface provides
 */
public interface IJpaDaoService<T, Id extends Serializable> {

    void persist(T entity);

    void update(T entity);

    /**
     * Inserts or updates provided collection of data into the databases
     *
     * @param <T>
     * @param entities
     * @return
     */
    <T extends IIdentifier> Collection<T> bulkSave(Collection<T> entities);

    /**
     * Inserts an object into the database, or updates it if it doesn't exists
     *
     * @param <T>
     * @param entity
     * @return
     */
    <T extends IIdentifier> T save(T entity);

    T findById(Id id);

    void delete(T entity);

    void delete(List<T> entities);

    List<T> findAll();

    void deleteAll();
}
