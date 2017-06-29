/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.Location;
import hr.diskobolos.persistence.ILocationPersistence;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class LocationPersistenceImpl extends ADaoPersistenceImpl<Location, Integer> implements ILocationPersistence {

    @Override
    protected Class<Location> getType() {
        return Location.class;
    }

    @Override
    public Location findByAddress(String address) {
        try {
            return entityManager.createNamedQuery(getType().getSimpleName() + ".findByAddress", getType())
                    .setParameter("address", address)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
