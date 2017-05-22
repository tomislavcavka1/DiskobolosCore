/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.persistence.IEvaluationQuestionDefPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class EvaluationQuestionDefPersistenceImpl extends ADaoPersistenceImpl<EvaluationQuestionDef, Integer> implements IEvaluationQuestionDefPersistence {

    @Override
    protected Class<EvaluationQuestionDef> getType() {
        return EvaluationQuestionDef.class;
    }

}
