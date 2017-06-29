package hr.diskobolos.model;

import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.EvaluationAnswer_;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef_;
import hr.diskobolos.model.evaluation.EvaluationQuestionnaireDefEnum;
import hr.diskobolos.model.evaluation.QuestionChoicesDef;
import hr.diskobolos.model.evaluation.QuestionChoicesDef_;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.model.evaluation.TermsOfConditionStatus;
import hr.diskobolos.util.StreamUtil;
import java.util.Arrays;
import static org.junit.Assert.assertNotNull;

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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Main JUnit test for model entity classes
 *
 * @author Tomislav Čavka
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ModelEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageSource messageSource;

    @Test
    public void fetchAllBankAccounts() throws Exception {
        TypedQuery<BankAccount> query = this.entityManager.getEntityManager().createNamedQuery("BankAccount.findAll",
                BankAccount.class);
        List<BankAccount> result = query.getResultList();
        assertNotNull(result);
    }

    @Test
    public void fetchAllEmails() throws Exception {
        TypedQuery<Email> query = this.entityManager.getEntityManager().createNamedQuery("Email.findAll",
                Email.class);
        List<Email> result = query.getResultList();
        assertNotNull(result);
    }

    @Test
    public void fetchAllMemberRegisters() throws Exception {
        TypedQuery<MemberRegister> query = this.entityManager.getEntityManager().createNamedQuery("MemberRegister.findAll",
                MemberRegister.class);
        List<MemberRegister> result = query.getResultList();
        assertNotNull(result);
    }

    @Test
    public void fetchTotalPointsPerMemberRegister() throws Exception {
        CriteriaBuilder cb = entityManager.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<EvaluationAnswer> evaluationAnswer = cq.from(EvaluationAnswer.class);
        Join<EvaluationAnswer, QuestionChoicesDef> choiceDef = evaluationAnswer.join(EvaluationAnswer_.answer);
        Join<QuestionChoicesDef, EvaluationQuestionDef> questionDef = choiceDef.join(QuestionChoicesDef_.evaluationQuestionDef);
        ParameterExpression<QuestionnaireType> questionnaireType = cb.parameter(QuestionnaireType.class, "questionnaireType");
        Expression<Integer> value = evaluationAnswer.get(EvaluationAnswer_.answer).get(QuestionChoicesDef_.value).as(Integer.class);
        cq.multiselect(evaluationAnswer.get(EvaluationAnswer_.memberRegister).alias("memberRegister"), value.alias("value"));
        cq.where(cb.equal(questionDef.get(EvaluationQuestionDef_.questionnaireType), questionnaireType));
        TypedQuery<Tuple> query = entityManager.getEntityManager().createQuery(cq);
        query.setParameter("questionnaireType", QuestionnaireType.CATEGORIZATION_OF_SPORTS_PER_SPORT_CLUB);
        List<Tuple> result = query.getResultList();
        Map<MemberRegister, Integer> pointsPerMemberRegister = result.stream().collect(Collectors.groupingBy(t -> {
            return t.get("memberRegister", MemberRegister.class);
        }, Collectors.summingInt(t -> t.get("value", Integer.class))));
        assertNotNull(pointsPerMemberRegister);
    }

    @Test
    public void fetchTermsOfCompetitionStatistic() {
        CriteriaBuilder cb = entityManager.getEntityManager().getCriteriaBuilder();
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
        TypedQuery<Long> query = entityManager.getEntityManager().createQuery(cq);
        query.setParameter("questionnaireType", QuestionnaireType.TERMS_OF_CONDITION);
        Long numberOfMemberRegistersWithoutTerms = query.getSingleResult();
        assertNotNull(numberOfMemberRegistersWithoutTerms);
    }

    @Test
    public void fetchNumberOfMemberRegistersWithValidAndInvalidTerm() {
        CriteriaBuilder cb = entityManager.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EvaluationAnswer> cq = cb.createQuery(EvaluationAnswer.class);
        Root<EvaluationAnswer> evaluationAnswer = cq.from(EvaluationAnswer.class);
        Join<EvaluationAnswer, QuestionChoicesDef> choiceDef = evaluationAnswer.join(EvaluationAnswer_.answer);
        Join<QuestionChoicesDef, EvaluationQuestionDef> questionDef = choiceDef.join(QuestionChoicesDef_.evaluationQuestionDef);
        ParameterExpression<QuestionnaireType> questionnaireType = cb.parameter(QuestionnaireType.class, "questionnaireType");
        cq.select(evaluationAnswer);
        cq.where(cb.equal(questionDef.get(EvaluationQuestionDef_.questionnaireType), questionnaireType));
        TypedQuery<EvaluationAnswer> query = entityManager.getEntityManager().createQuery(cq);
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
        assertNotNull(evaluationAnswers);
    }

}
