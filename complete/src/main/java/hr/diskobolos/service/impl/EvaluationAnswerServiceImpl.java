/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.dto.CategorizationOfSportsPerSportClubDto;
import hr.diskobolos.dto.RankingAndCategorizationOfSportsDto;
import hr.diskobolos.dto.TermsOfCompetitionDto;
import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.EvaluationQuestionnaireDefEnum;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.model.evaluation.TermsOfConditionStatus;
import hr.diskobolos.persistence.IEvaluationAnswerPersistence;
import hr.diskobolos.service.IEvaluationAnswerService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class EvaluationAnswerServiceImpl implements IEvaluationAnswerService {

    @Autowired
    IEvaluationAnswerPersistence evaluationAnswerPersistence;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void persist(EvaluationAnswer entity) {
        evaluationAnswerPersistence.persist(entity);
    }

    @Override
    public void update(EvaluationAnswer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends IIdentifier> Collection<T> bulkSave(Collection<T> entities) {
        return evaluationAnswerPersistence.bulkSave(entities);
    }

    @Override
    public <T extends IIdentifier> T save(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EvaluationAnswer findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(EvaluationAnswer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(List<EvaluationAnswer> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EvaluationAnswer> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EvaluationAnswer> findAllByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType) {
        return evaluationAnswerPersistence.findAllByMemberRegisterAndQuestionnaireType(memberRegister, questionnaireType);
    }

    @Override
    public TermsOfCompetitionDto fetchTermsOfCompetitionByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType) {
        TermsOfCompetitionDto termsOfCompetitionDto = new TermsOfCompetitionDto();
        termsOfCompetitionDto.setId(memberRegister.getId());
        termsOfCompetitionDto.setName(memberRegister.getName());
        termsOfCompetitionDto.setAddress(memberRegister.getLocation().getAddress());
        termsOfCompetitionDto.setRegisterNumber(memberRegister.getRegisterNumber());
        termsOfCompetitionDto.setRegistrationDate(memberRegister.getRegistrationDate());

        List<EvaluationAnswer> evaluationAnswers = evaluationAnswerPersistence.findAllByMemberRegisterAndQuestionnaireType(memberRegister, questionnaireType);

        List<EvaluationQuestionnaireDefEnum> questionnaireDef = Arrays.asList(EvaluationQuestionnaireDefEnum.values());
        Long numberOfQuestion = questionnaireDef.stream().filter(q -> q.getQuestionnaireType().equals(QuestionnaireType.TERMS_OF_CONDITION)).collect(Collectors.counting());
        termsOfCompetitionDto.setQuestionnairePercentage(getQuestionnairePercentage(evaluationAnswers, numberOfQuestion, questionnaireDef));

        TermsOfConditionStatus termsOfConditionStatus = TermsOfConditionStatus.NONE;
        if (!evaluationAnswers.isEmpty() && evaluationAnswers.size() == numberOfQuestion) {
            boolean isValid = evaluationAnswers.stream().allMatch(e -> e.getAnswer().getLabel().equals(messageSource.getMessage("QuestionChoicesDef.yes", null, Locale.ENGLISH)));
            termsOfConditionStatus = isValid ? TermsOfConditionStatus.VALID : TermsOfConditionStatus.INVALID;
        }
        termsOfCompetitionDto.setTermsOfConditionStatus(termsOfConditionStatus.name());
        return termsOfCompetitionDto;
    }

    @Override
    public RankingAndCategorizationOfSportsDto fetchRankingAndCategorizationOfSportsByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType) {
        RankingAndCategorizationOfSportsDto rankingAndCategorizationOfSportsDto = new RankingAndCategorizationOfSportsDto();
        rankingAndCategorizationOfSportsDto.setId(memberRegister.getId());
        rankingAndCategorizationOfSportsDto.setName(memberRegister.getName());

        List<EvaluationAnswer> evaluationAnswers = evaluationAnswerPersistence.findAllByMemberRegisterAndQuestionnaireType(memberRegister, questionnaireType);
        Integer totalPoints = 0;

        if (!evaluationAnswers.isEmpty()) {

            totalPoints = evaluationAnswers.stream().mapToInt(e -> Integer.valueOf(e.getAnswer().getValue())).sum();

            evaluationAnswers.forEach((EvaluationAnswer e) -> {
                EvaluationQuestionnaireDefEnum question = e.getAnswer().getEvaluationQuestionDef().getQuestion();
                switch (question) {
                    case THE_CRITERION_OF_SPORT_DEVELOPMENT_INTERNATIONAL_FEDERATION:
                        rankingAndCategorizationOfSportsDto.setCriterionOfSportInternationalFederation(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case THE_CRITERION_OF_SPORT_DEVELOPMENT_NATIONAL_ALLIANCE:
                        rankingAndCategorizationOfSportsDto.setCriterionOfSportNationalAlliance(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case THE_CRITERION_OF_SPORT_DEVELOPMENT_COUNTY_ALLIANCE:
                        rankingAndCategorizationOfSportsDto.setCriterionOfSportCountyAlliance(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case THE_CRITERION_OF_SPORT_DEVELOPMENT_CITY_OF_ZADAR:
                        rankingAndCategorizationOfSportsDto.setCriterionOfSportCityOfZadar(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case PUBLIC_INTEREST_OF_SPORT_PUBLIC_INTEREST:
                        rankingAndCategorizationOfSportsDto.setSportStatusOfPublicInterest(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case PUBLIC_INTEREST_OF_SPORT_TOWN_ZADAR:
                        rankingAndCategorizationOfSportsDto.setSportStatusTownZadar(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case PUBLIC_INTEREST_OF_SPORT_OLYMPIC_STATUS:
                        rankingAndCategorizationOfSportsDto.setOlympicSportsStatus(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case PUBLIC_INTEREST_OF_SPORT_IMPORTANCE_FOR_TEACHING_TZK:
                        rankingAndCategorizationOfSportsDto.setImportanceForTeachingTzk(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case SPORTS_QUALITY_NUMBER_CATEGORIZED_ATHLETES:
                        rankingAndCategorizationOfSportsDto.setSportsQualityNumberOfCategorizedAthletes(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case SPORTS_QUALITY_ACHIEVED_SPORTS_RESULTS_IN_CROATIA:
                        rankingAndCategorizationOfSportsDto.setSportsQualityAccomplishedSportsResultsCroatia(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case SPORTS_QUALITY_ACHIEVED_SPORTS_RESULTS_IN_TOWN_ZADAR:
                        rankingAndCategorizationOfSportsDto.setSportsQualityAccomplishedSportsResultsTownOfZadar(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    default:
                        break;
                }
            });
        }

        List<EvaluationQuestionnaireDefEnum> questionnaireDef = Arrays.asList(EvaluationQuestionnaireDefEnum.values());
        Long numberOfQuestion = questionnaireDef.stream().filter(q -> q.getQuestionnaireType().equals(QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS)).collect(Collectors.counting());
        rankingAndCategorizationOfSportsDto.setQuestionnairePercentage(getQuestionnairePercentage(evaluationAnswers, numberOfQuestion, questionnaireDef));

        rankingAndCategorizationOfSportsDto.setTotalPoints(totalPoints);

        return rankingAndCategorizationOfSportsDto;
    }

    @Override
    public CategorizationOfSportsPerSportClubDto fetchCategorizationOfSportsPerSportClubByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType) {
        CategorizationOfSportsPerSportClubDto categorizationOfSportsPerSportClubDto = new CategorizationOfSportsPerSportClubDto();
        categorizationOfSportsPerSportClubDto.setId(memberRegister.getId());
        categorizationOfSportsPerSportClubDto.setName(memberRegister.getName());

        List<EvaluationAnswer> evaluationAnswers = evaluationAnswerPersistence.findAllByMemberRegisterAndQuestionnaireType(memberRegister, questionnaireType);
        Integer totalPoints = 0;

        if (!evaluationAnswers.isEmpty()) {

            totalPoints = evaluationAnswers.stream().mapToInt(e -> Integer.valueOf(e.getAnswer().getValue())).sum();

            evaluationAnswers.forEach((EvaluationAnswer e) -> {
                EvaluationQuestionnaireDefEnum question = e.getAnswer().getEvaluationQuestionDef().getQuestion();
                switch (question) {
                    case CATEGORIZATION_ACCORDING_TO_NUMBER_OF_REGISTERED_CLUBS_IN_NATIONAL_FEDERATION:
                        categorizationOfSportsPerSportClubDto.setCategorizationOnNationalFederationLevel(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case CATEGORIZATION_BY_TYPE_OF_SPORT:
                        categorizationOfSportsPerSportClubDto.setCategorizationOnSportTypeLevel(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case CATEGORIZATION_ACCORDING_TO_AGE_CATEGORIES:
                        categorizationOfSportsPerSportClubDto.setCategorizationAccordingToAge(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case CATEGORIZATION_ACCORDING_TO_RANK_OF_COMPETITION_SYSTEM:
                        categorizationOfSportsPerSportClubDto.setCategorizationOnCompetitionSystemLevel(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case CATEGORIZATION_ACCORDING_TO_NUMBER_OF_TEAM_MEMBERS_COMPETING:
                        categorizationOfSportsPerSportClubDto.setCategorizationBasedOnNumberOfTeamMembersCompeting(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case CATEGORIZATION_ACCORDING_TO_MASS_IN_SPORTS_SCHOOLS:
                        categorizationOfSportsPerSportClubDto.setCategorizationAccordingToMassInSportsSchools(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case CATEGORIZATION_ACCORDING_TO_PROFESSIONAL_STAFF:
                        categorizationOfSportsPerSportClubDto.setCategorizationAccordingToProfessionalStaff(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case CATEGORIZATION_ACCORDING_TO_TRADITION_OF_TOWN_ZADAR:
                        categorizationOfSportsPerSportClubDto.setCategorizationAccordingToTraditionOfTownZadar(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    case COFFICIENCY_OF_SPORTS_CATEGORY:
                        categorizationOfSportsPerSportClubDto.setCofficiencyOfSportsCategory(Integer.valueOf(e.getAnswer().getValue()));
                        break;
                    default:
                        break;
                }
            });
        }

        List<EvaluationQuestionnaireDefEnum> questionnaireDef = Arrays.asList(EvaluationQuestionnaireDefEnum.values());
        Long numberOfQuestion = questionnaireDef.stream().filter(q -> q.getQuestionnaireType().equals(QuestionnaireType.CATEGORIZATION_OF_SPORTS_PER_SPORT_CLUB)).collect(Collectors.counting());
        categorizationOfSportsPerSportClubDto.setQuestionnairePercentage(getQuestionnairePercentage(evaluationAnswers, numberOfQuestion, questionnaireDef));

        categorizationOfSportsPerSportClubDto.setTotalPoints(totalPoints);

        return categorizationOfSportsPerSportClubDto;
    }

    private Float getQuestionnairePercentage(List<EvaluationAnswer> evaluationAnswers, Long numberOfQuestion, List<EvaluationQuestionnaireDefEnum> questionnaireDef) {
        Long numberOfAnsweredQuestions = evaluationAnswers.stream().filter(e -> questionnaireDef.contains(e.getAnswer().getEvaluationQuestionDef().getQuestion())).collect(Collectors.counting());
        Float questionnairePercentage = ((float) numberOfAnsweredQuestions / numberOfQuestion) * 100;
        return new BigDecimal(Float.toString(questionnairePercentage)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
