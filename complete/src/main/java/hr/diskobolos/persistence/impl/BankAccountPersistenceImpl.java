/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.BankAccount;
import hr.diskobolos.persistence.IBankAccountPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class BankAccountPersistenceImpl extends ADaoPersistenceImpl<BankAccount, Integer> implements IBankAccountPersistence {

    @Override
    protected Class<BankAccount> getType() {
        return BankAccount.class;
    }

}
