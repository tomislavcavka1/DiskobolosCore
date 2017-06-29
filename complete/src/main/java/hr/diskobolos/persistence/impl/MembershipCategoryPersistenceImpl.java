/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.MembershipCategory;
import hr.diskobolos.persistence.IMembershipCategoryPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class MembershipCategoryPersistenceImpl extends ADaoPersistenceImpl<MembershipCategory, Integer> implements IMembershipCategoryPersistence {

    @Override
    protected Class<MembershipCategory> getType() {
        return MembershipCategory.class;
    }

    @Override
    public Long getNumberOfMembershipCategories() {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".getNumberOfMembershipCategories", Long.class).getSingleResult();
    }

}
