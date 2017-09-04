package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.Sport;
import hr.diskobolos.persistence.ISportPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class SportPersistenceImpl extends ADaoPersistenceImpl<Sport, Integer> implements ISportPersistence {

    @Override
    protected Class<Sport> getType() {
        return Sport.class;
    }

    @Override
    public Long getNumberOfSports() {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".getNumberOfSports", Long.class).getSingleResult();
    }

    @Override
    public Sport findSportByName(String name) {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".findSportByName", Sport.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
