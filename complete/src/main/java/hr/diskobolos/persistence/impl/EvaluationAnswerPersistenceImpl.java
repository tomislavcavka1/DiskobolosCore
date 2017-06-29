/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence.impl;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.EvaluationAnswer_;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef_;
import hr.diskobolos.model.evaluation.EvaluationQuestionnaireDefEnum;
import hr.diskobolos.model.evaluation.QuestionChoicesDef;
import hr.diskobolos.model.evaluation.QuestionChoicesDef_;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.model.evaluation.TermsOfConditionStatus;
import hr.diskobolos.persistence.IEvaluationAnswerPersistence;
import hr.diskobolos.util.StreamUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomislav Čavka
 */
@Repository
@Transactional
public class EvaluationAnswerPersistenceImpl extends ADaoPersistenceImpl<EvaluationAnswer, Integer> implements IEvaluationAnswerPersistence {

    @Autowired
    private MessageSource messageSource;

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

    @Override
    public List<EvaluationAnswer> findAllByQuestionnaireType(QuestionnaireType questionnaireType) {
        return entityManager.createNamedQuery(getType().getSimpleName() + ".findAllByQuestionnaireType", getType())
                .setParameter("questionnaireType", questionnaireType)
                .getResultList();
    }

    @Override
    public Map<MemberRegister, Integer> fetchTotalPointsPerMemberRegister(QuestionnaireType questionnaireType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<EvaluationAnswer> evaluationAnswer = cq.from(EvaluationAnswer.class);
        Join<EvaluationAnswer, QuestionChoicesDef> choiceDef = evaluationAnswer.join(EvaluationAnswer_.answer);
        Join<QuestionChoicesDef, EvaluationQuestionDef> questionDef = choiceDef.join(QuestionChoicesDef_.evaluationQuestionDef);
        ParameterExpression<QuestionnaireType> questionnaireTypeParam = cb.parameter(QuestionnaireType.class, "questionnaireType");
        Expression<Integer> value = evaluationAnswer.get(EvaluationAnswer_.answer).get(QuestionChoicesDef_.value).as(Integer.class);
        cq.multiselect(evaluationAnswer.get(EvaluationAnswer_.memberRegister).alias("memberRegister"), value.alias("value"));
        cq.where(cb.equal(questionDef.get(EvaluationQuestionDef_.questionnaireType), questionnaireTypeParam));
        TypedQuery<Tuple> query = entityManager.createQuery(cq);
        query.setParameter("questionnaireType", questionnaireType);
        List<Tuple> result = query.getResultList();
        Map<MemberRegister, Integer> pointsPerMemberRegister = result.stream().collect(Collectors.groupingBy(t -> {
            return t.get("memberRegister", MemberRegister.class);
        }, Collectors.summingInt(t -> t.get("value", Integer.class))));

        return pointsPerMemberRegister;
    }

    @Override
    public Long fetchNumberOfMemberRegistersWithoutTerms() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<MemberRegister> memberRegister = cq.from(MemberRegister.class);
        Subquery<Long> sq = cq.subquery(Long.class);
        Root<EvaluationAnswer> evaluationAnswer = sq.from(EvaluationAnswer.class);
        Join<EvaluationAnswer, QuestionChoicesDef> choiceDef = evaluationAnswer.join(EvaluationAnswer_.answer);
        Join<QuestionChoicesDef, EvaluationQuestionDef> questionDef = choiceDef.join(QuestionChoicesDef_.evaluationQuestionDef);
        ParameterExpression<QuestionnaireType> questionnaireType = cb.parameter(QuestionnaireType.class, "questionnaireType");
        sq.select(evaluationAnswer.get("memberRegister").get("id"))
                .where(cb.equal(questionDef.get(EvaluationQuestionDef_.questionnaireType), questionnaireType));
        cq.select(cb.count(memberRegister.get("id")))
                .where(cb.not(cb.in(memberRegister.get("id")).value(sq)));
        TypedQuery<Long> query = entityManager.createQuery(cq);
        query.setParameter("questionnaireType", QuestionnaireType.TERMS_OF_CONDITION);
        return query.getSingleResult();
    }

    @Override
    public ConcurrentMap<TermsOfConditionStatus, AtomicLong> fetchTermsOfCompetitionStatistic() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EvaluationAnswer> cq = cb.createQuery(EvaluationAnswer.class);
        Root<EvaluationAnswer> evaluationAnswer = cq.from(EvaluationAnswer.class);
        Join<EvaluationAnswer, QuestionChoicesDef> choiceDef = evaluationAnswer.join(EvaluationAnswer_.answer);
        Join<QuestionChoicesDef, EvaluationQuestionDef> questionDef = choiceDef.join(QuestionChoicesDef_.evaluationQuestionDef);
        ParameterExpression<QuestionnaireType> questionnaireType = cb.parameter(QuestionnaireType.class, "questionnaireType");
        cq.select(evaluationAnswer);
        cq.where(cb.equal(questionDef.get(EvaluationQuestionDef_.questionnaireType), questionnaireType));
        TypedQuery<EvaluationAnswer> query = entityManager.createQuery(cq);
        query.setParameter("questionnaireType", QuestionnaireType.TERMS_OF_CONDITION);
        List<EvaluationAnswer> evaluationAnswers = query.getResultList();

        ConcurrentMap<TermsOfConditionStatus, AtomicLong> distributionByTermsOfCompetitionStatus = new ConcurrentHashMap<>();

        List<EvaluationQuestionnaireDefEnum> questionnaireDef = Arrays.asList(EvaluationQuestionnaireDefEnum.values());
        long numberOfQuestion = questionnaireDef.stream().filter(q -> q.getQuestionnaireType().equals(QuestionnaireType.TERMS_OF_CONDITION)).collect(Collectors.counting());

        List<MemberRegister> memberRegisters = evaluationAnswers.stream()
                .filter(StreamUtil.distinctByKey((EvaluationAnswer e) -> e.getMemberRegister().getId()))
                .map(EvaluationAnswer::getMemberRegister)
                .collect(Collectors.toList());
        memberRegisters.stream().forEach((memberRegister) -> {
            TermsOfConditionStatus termsOfConditionStatus = TermsOfConditionStatus.NONE;
            if (evaluationAnswers.stream().filter(m -> m.getMemberRegister().equals(memberRegister)).count() == numberOfQuestion) {
                boolean isValid = evaluationAnswers.stream()
                        .filter(m -> m.getMemberRegister().equals(memberRegister))
                        .allMatch(e -> e.getAnswer().getLabel().equals(messageSource.getMessage("QuestionChoicesDef.yes", null, Locale.ENGLISH)));
                termsOfConditionStatus = isValid ? TermsOfConditionStatus.VALID : TermsOfConditionStatus.INVALID;
            }
            distributionByTermsOfCompetitionStatus.putIfAbsent(termsOfConditionStatus, new AtomicLong(0));
            distributionByTermsOfCompetitionStatus.get(termsOfConditionStatus).incrementAndGet();
        });

        return distributionByTermsOfCompetitionStatus;
    }

}
