/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.NomenclatureOfSport;
import hr.diskobolos.persistence.INomenclatureOfSportPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Cavka
 */
@Repository
@Transactional
public class NomenclatureOfSportPersistenceImpl extends ADaoPersistenceImpl<NomenclatureOfSport, Integer> implements INomenclatureOfSportPersistence {

    @Override
    protected Class<NomenclatureOfSport> getType() {
        return NomenclatureOfSport.class;
    }

}
