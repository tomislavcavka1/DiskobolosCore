/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.FinancialResources;
import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.persistence.IFinancialResourcesPersistence;
import hr.diskobolos.service.IFinancialResourcesService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class FinancialResourcesServiceImpl implements IFinancialResourcesService {

    @Autowired
    IFinancialResourcesPersistence financialResourcesPersistence;

    @Override
    public void persist(FinancialResources entity) {
        financialResourcesPersistence.persist(entity);
    }

    @Override
    public void update(FinancialResources entity) {
        financialResourcesPersistence.update(entity);
    }

    @Override
    public <T extends IIdentifier> Collection<T> bulkSave(Collection<T> entities) {
        return financialResourcesPersistence.bulkSave(entities);
    }

    @Override
    public <T extends IIdentifier> T save(T entity) {
        return financialResourcesPersistence.save(entity);
    }

    @Override
    public FinancialResources findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(FinancialResources entity) {
        financialResourcesPersistence.delete(entity);
    }

    @Override
    public void delete(List<FinancialResources> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FinancialResources> findAll() {
        return financialResourcesPersistence.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
