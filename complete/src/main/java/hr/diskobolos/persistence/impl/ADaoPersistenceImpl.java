/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.persistence.IJpaDaoPersistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract class that implements main CRUD JPA methods
 *
 * @author Tomislav Čavka
 *
 * @param <T> the type of object this interface provides
 * @param <Id> the type of Id this interface provides
 */
@Repository
@Transactional
public abstract class ADaoPersistenceImpl<T, Id extends Serializable> implements IJpaDaoPersistence<T, Id> {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected abstract Class<T> getType();

    public T findOne(Integer id) {
        return entityManager.find(getType(), id);
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public T findById(Id id) {
        return entityManager.find(getType(), id);
    }

    @Override
    public void delete(T entity) {
        T entityForDeletion = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        entityManager.remove(entityForDeletion);
    }

    @Override
    public void delete(List<T> entities) {
        if (!entities.isEmpty()) {
            Query query = entityManager.createNamedQuery(getType().getSimpleName() + ".deleteItems");
            query.setParameter("forDeletion", entities);
            query.executeUpdate();
        }
    }

    @Override
    public List<T> findAll() {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".findAll", getType())
                .getResultList();
    }

    @Override
    public void deleteAll() {
        entityManager.createNamedQuery(getType().getSimpleName() + ".deleteAll", getType()).executeUpdate();
    }
}
