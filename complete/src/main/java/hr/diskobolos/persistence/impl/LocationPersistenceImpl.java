/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.Location;
import hr.diskobolos.persistence.ILocationPersistence;
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

}
