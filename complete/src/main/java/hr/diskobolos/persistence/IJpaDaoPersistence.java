package hr.diskobolos.persistence;

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

    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();
}
