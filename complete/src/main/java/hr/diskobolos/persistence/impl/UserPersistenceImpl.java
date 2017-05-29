/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.security.User;
import hr.diskobolos.persistence.IUserPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class UserPersistenceImpl extends ADaoPersistenceImpl<User, Integer> implements IUserPersistence {

    @Override
    protected Class<User> getType() {
        return User.class;
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".findByUsername", getType())
                .setParameter("username", username)
                .getSingleResult();
    }

}
