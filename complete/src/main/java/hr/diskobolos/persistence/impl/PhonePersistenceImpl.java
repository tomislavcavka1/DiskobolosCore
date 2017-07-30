/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.Phone;
import hr.diskobolos.persistence.IPhonePersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class PhonePersistenceImpl extends ADaoPersistenceImpl<Phone, Integer> implements IPhonePersistence {

    @Override
    protected Class<Phone> getType() {
        return Phone.class;
    }

}
