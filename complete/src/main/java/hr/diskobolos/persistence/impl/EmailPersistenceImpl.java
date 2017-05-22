/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.Email;
import hr.diskobolos.persistence.IEmailPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class EmailPersistenceImpl extends ADaoPersistenceImpl<Email, Integer> implements IEmailPersistence {

    @Override
    protected Class<Email> getType() {
        return Email.class;
    }

}
