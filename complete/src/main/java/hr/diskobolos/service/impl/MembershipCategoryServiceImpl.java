/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.model.MembershipCategory;
import hr.diskobolos.persistence.IMembershipCategoryPersistence;
import hr.diskobolos.service.IMembershipCategoryService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class MembershipCategoryServiceImpl implements IMembershipCategoryService {

    @Autowired
    IMembershipCategoryPersistence membershipCategoryPersistence;

    @Override
    public List<MembershipCategory> findAll() {
        return membershipCategoryPersistence.findAll();
    }

    @Override
    public void persist(MembershipCategory entity) {
        membershipCategoryPersistence.persist(entity);
    }

    @Override
    public void update(MembershipCategory entity) {
        membershipCategoryPersistence.update(entity);
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
    public MembershipCategory findById(Integer id) {
        return membershipCategoryPersistence.findById(id);
    }

    @Override
    public void delete(MembershipCategory entity) {
        membershipCategoryPersistence.delete(entity);
    }

    @Override
    public void delete(List<MembershipCategory> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public MembershipCategory findMembershipCategoryByDescription(String description) {
        return membershipCategoryPersistence.findMembershipCategoryByDescription(description);
    }

}
