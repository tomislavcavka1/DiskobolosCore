package hr.diskobolos.persistence;

import hr.diskobolos.model.IIdentifier;
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
public interface IJpaDaoPersistence<T, Id extends Serializable> {

    void persist(T entity);

    void update(T entity);

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
