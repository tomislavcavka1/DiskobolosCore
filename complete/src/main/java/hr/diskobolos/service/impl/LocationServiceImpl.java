/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.Location;
import hr.diskobolos.persistence.ILocationPersistence;
import hr.diskobolos.service.ILocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class LocationServiceImpl implements ILocationService {

    @Autowired
    ILocationPersistence locationPersistence;

    @Override
    public void persist(Location entity) {
        locationPersistence.persist(entity);
    }

    @Override
    public void update(Location entity) {
        locationPersistence.update(entity);
    }

    @Override
    public Location findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Location entity) {
        locationPersistence.delete(entity);
    }

    @Override
    public void delete(List<Location> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> findAll() {
        return locationPersistence.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
