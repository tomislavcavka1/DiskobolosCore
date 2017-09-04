/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.model.security.User;
import hr.diskobolos.persistence.IUserPersistence;
import hr.diskobolos.service.IUserService;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserPersistence userPersistence;

    @Override
    public void persist(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User entity) {
        userPersistence.update(entity);
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
    public User findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(List<User> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findByUsername(String username) {
        return userPersistence.findByUsername(username);
    }

    @Override
    public void postAuthorizationUpdate(Integer id) {
        User user = userPersistence.findById(id);
        if (!user.isAccountLocked()) {
            user.setLastLogin(new Date());
            user.setAccountLocked(true);
            // update last login time
            userPersistence.update(user);
        }
    }

    @Override
    public void preLogoutActions(Integer id) {
        User user = userPersistence.findById(id);
        if (user.isAccountLocked()) {
            user.setLastLogout(new Date());
            user.setAccountLocked(false);
            // update last login time
            userPersistence.update(user);
        }
    }

}
