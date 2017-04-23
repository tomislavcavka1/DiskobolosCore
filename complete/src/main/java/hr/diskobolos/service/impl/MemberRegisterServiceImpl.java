/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.persistence.IMemberRegisterPersistence;
import hr.diskobolos.service.IMemberRegisterService;
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
    IMemberRegisterPersistence membershipCategoryPersistence;

    @Override
    public void persist(MemberRegister entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MemberRegister entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MemberRegister findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MemberRegister entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MemberRegister> findAll() {
        return membershipCategoryPersistence.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
