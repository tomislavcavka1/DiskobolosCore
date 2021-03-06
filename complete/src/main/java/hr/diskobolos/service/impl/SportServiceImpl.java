/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.model.Sport;
import hr.diskobolos.persistence.ISportPersistence;
import hr.diskobolos.service.ISportService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class SportServiceImpl implements ISportService {

    @Autowired
    ISportPersistence sportPersistence;

    @Override
    public void persist(Sport entity) {
        sportPersistence.persist(entity);
    }

    @Override
    public void update(Sport entity) {
        sportPersistence.update(entity);
    }

    @Override
    public <T extends IIdentifier> Collection<T> bulkSave(Collection<T> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends IIdentifier> T save(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sport findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Sport entity) {
        sportPersistence.delete(entity);
    }

    @Override
    public void delete(List<Sport> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sport> findAll() {
        return sportPersistence.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sport findSportByName(String name) {
        return sportPersistence.findSportByName(name);
    }

}
