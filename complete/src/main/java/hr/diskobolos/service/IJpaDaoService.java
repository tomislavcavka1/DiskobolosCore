/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service;

import java.io.Serializable;
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

    T findById(Id id);

    void delete(T entity);

    void delete(List<T> entities);

    List<T> findAll();

    void deleteAll();
}
