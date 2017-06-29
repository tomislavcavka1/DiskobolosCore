/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.persistence.IMemberRegisterPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class MemberRegisterPersistenceImpl extends ADaoPersistenceImpl<MemberRegister, Integer> implements IMemberRegisterPersistence {

    @Override
    protected Class<MemberRegister> getType() {
        return MemberRegister.class;
    }

    @Override
    public Long getNumberOfMemberRegisters() {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".getNumberOfMemberRegisters", Long.class).getSingleResult();
    }
}
