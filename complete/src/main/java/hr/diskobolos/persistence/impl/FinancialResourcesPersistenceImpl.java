/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.FinancialResources;
import hr.diskobolos.persistence.IFinancialResourcesPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class FinancialResourcesPersistenceImpl extends ADaoPersistenceImpl<FinancialResources, Integer> implements IFinancialResourcesPersistence {

    @Override
    protected Class<FinancialResources> getType() {
        return FinancialResources.class;
    }

}
