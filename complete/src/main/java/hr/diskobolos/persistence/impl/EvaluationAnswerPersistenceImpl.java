/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.persistence.IEvaluationAnswerPersistence;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class EvaluationAnswerPersistenceImpl extends ADaoPersistenceImpl<EvaluationAnswer, Integer> implements IEvaluationAnswerPersistence {

    @Override
    protected Class<EvaluationAnswer> getType() {
        return EvaluationAnswer.class;
    }

    @Override
    public List<EvaluationAnswer> findAllByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType) {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".findAllByMemberRegisterAndQuestionnaireType", getType())
                .setParameter("memberRegister", memberRegister)
                .setParameter("questionnaireType", questionnaireType)
                .getResultList();
    }
}
