package hr.diskobolos.model.evaluation;

/**
 * Defines questionnaire for the evaluation module
 *
 * @author Tomislav Čavka
 */
public enum EvaluationQuestionnaireDefEnum {
    /*
     * Terms of competition questionnaire
     */
    IS_REGISTERED(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_1),
    IN_OPERATION_LEAST_TWO_YEARS(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_1),
    HEADQUARTERS_IN_ZADAR(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_1),
    APPLIES_TRANSPARENT_BUSINESS(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_1),
    HAS_ENSURED_HUMAN_AND_MATERIAL_RESOURCES(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_1),
    ENTERED_IN_THE_REGISTER_OF_SPORTS_ACTIVITIES(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_1),
    IS_MEMBER_OF_THE_CITY_COUNTY_AND_NATIONAL_SPORTS_FEDERATION(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_1),
    PARTICIPATES_IN_THE_COMPETITION_SYSTEM_OF_NATIONAL_FEDERATION(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_2),
    COMMITTED_WORK_PLAN_AND_PROGRAM_FOR_NEXT_YEAR(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_2),
    COMMITTED_WORK_REPORT_FOR_PREVIOUS_YEAR(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_2),
    COMMITTED_FINANCIAL_PLAN_FOR_PREVIOUS_YEAR(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_2),
    EXTRACT_FROM_THE_REGISTER_OF_REPUBLIC_CROATIA(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    EXTRACT_FROM_COURT_REGISTER(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    COPY_OF_THE_STAMP_FOR_PROFESSIONAL_QUALIFICATION(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    COPY_OF_THE_MINUTES_FROM_LAST_ASSEMBLY(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    COPY_OF_NOTICE_OF_ENTRY_INTO_REGISTER_OF_SPORTS_ACTIVITIES(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    CERTIFICATE_OF_ENTRY_INTO_REGISTER_OF_NON_PROFIT_ORGANIZATIONS(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    PROGRAM_PROPOSAL_WITH_DETAILED_EXPLANATION(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_2),
    CERTIFICATE_OF_TRUTHFULNESS_AND_ACCURACY_OF_DATA(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_2),
    PROFESSIONAL_ASSOCIATION_WHOSE_ACTIVITY_IS_NOT_SPORT_RELATED(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.AUTOMATIC_DEACTIVATION_OF_A_MEMBER),
    AN_ASSOCIATION_THAT_IS_IN_BANKRUPTCY(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.AUTOMATIC_DEACTIVATION_OF_A_MEMBER),
    AN_ASSOCIATION_THAT_IS_ONE_OF_FOUNDERS_OF_POLITICAL_PARTY(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.AUTOMATIC_DEACTIVATION_OF_A_MEMBER),
    FINANCIAL_REPORT_IS_PUBLISHED_THROUGH_FINA_REGISTER(QuestionInputType.RADIO, QuestionnaireType.TERMS_OF_CONDITION, QuestionGroup.GENERAL_CONDITIONS_2),
    /*
     * Ranking and categorization of sports questionnaire
     */
    THE_CRITERION_OF_SPORT_DEVELOPMENT_INTERNATIONAL_FEDERATION(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.INTERNATIONAL_FEDERATION),
    THE_CRITERION_OF_SPORT_DEVELOPMENT_NATIONAL_ALLIANCE(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.NATIONAL_ALLIANCE),
    THE_CRITERION_OF_SPORT_DEVELOPMENT_COUNTY_ALLIANCE(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.COUNTY_ALLIANCE),
    THE_CRITERION_OF_SPORT_DEVELOPMENT_CITY_OF_ZADAR(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.CITY_OF_ZADAR),
    PUBLIC_INTEREST_OF_SPORT_PUBLIC_INTEREST(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.PUBLIC_INTEREST),
    PUBLIC_INTEREST_OF_SPORT_TOWN_ZADAR(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.TRADITION_OF_SPORT_ZADAR),
    PUBLIC_INTEREST_OF_SPORT_OLYMPIC_STATUS(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.OLYMPIC_SPORTS_STATUS),
    PUBLIC_INTEREST_OF_SPORT_IMPORTANCE_FOR_TEACHING_TZK(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.IMPORTANCE_FOR_TEACHING_TZK),
    SPORTS_QUALITY_NUMBER_CATEGORIZED_ATHLETES(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.NUMBER_OF_CATEGORIZED_ATHLETES),
    SPORTS_QUALITY_ACHIEVED_SPORTS_RESULTS_IN_CROATIA(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.ACHIEVED_SPORTS_RESULTS_IN_CROATIA),
    SPORTS_QUALITY_ACHIEVED_SPORTS_RESULTS_IN_TOWN_ZADAR(QuestionInputType.RADIO, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS, QuestionGroup.ACHIEVED_SPORTS_RESULTS_IN_TOWN_ZADAR);
    
    private final QuestionInputType type;
    private final QuestionnaireType questionnaireType;
    private final QuestionGroup group;

    private EvaluationQuestionnaireDefEnum(QuestionInputType type, QuestionnaireType questionnaireType, QuestionGroup group) {
        this.type = type;
        this.questionnaireType = questionnaireType;
        this.group = group;
    }

    public QuestionInputType getType() {
        return type;
    }

    public QuestionnaireType getQuestionnaireType() {
        return questionnaireType;
    }

    public QuestionGroup getGroup() {
        return group;
    }

    public String getName() {
        return this.name();
    }

    public String getLocalizationKey() {
        return this.getClass().getSimpleName() + "." + this.name();
    }

    public static EvaluationQuestionnaireDefEnum getInstance(String type) {
        for (EvaluationQuestionnaireDefEnum method : EvaluationQuestionnaireDefEnum.values()) {
            if (type.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }
}
