/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.model.evaluation.TermsOfConditionStatus;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Tomislav Čavka
 */
public interface IEvaluationAnswerPersistence extends IJpaDaoPersistence<EvaluationAnswer, Integer> {

    List<EvaluationAnswer> findAllByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType);

    List<EvaluationAnswer> findAllByQuestionnaireType(QuestionnaireType questionnaireType);

    Map<MemberRegister, Integer> fetchTotalPointsPerMemberRegister(QuestionnaireType questionnaireType);

    Long fetchNumberOfMemberRegistersWithoutTerms();

    ConcurrentMap<TermsOfConditionStatus, AtomicLong> fetchTermsOfCompetitionStatistic();
}
