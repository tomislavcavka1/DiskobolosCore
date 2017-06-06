/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.persistence.IMemberRegisterPersistence;
import hr.diskobolos.service.IMemberRegisterService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class MemberRegisterServiceImpl implements IMemberRegisterService {

    @Autowired
    IMemberRegisterPersistence membershipRegisterPersistence;

    @Override
    public void persist(MemberRegister entity) {
        membershipRegisterPersistence.persist(entity);
    }

    @Override
    public void update(MemberRegister entity) {
        membershipRegisterPersistence.update(entity);
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
    public MemberRegister findById(Integer id) {
        return membershipRegisterPersistence.findById(id);
    }

    @Override
    public void delete(MemberRegister entity) {
        membershipRegisterPersistence.delete(entity);
    }

    @Override
    public void delete(List<MemberRegister> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MemberRegister> findAll() {
        return membershipRegisterPersistence.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
