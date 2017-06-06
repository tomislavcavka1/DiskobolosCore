package hr.diskobolos.model.evaluation;

/**
 * Defines questionnaire for the evaluation module
 *
 * @author Tomislav Čavka
 */
public enum EvaluationQuestionnaireDefEnum {
    IS_REGISTERED(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_1),
    IN_OPERATION_LEAST_TWO_YEARS(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_1),
    HEADQUARTERS_IN_ZADAR(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_1),
    APPLIES_TRANSPARENT_BUSINESS(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_1),
    HAS_ENSURED_HUMAN_AND_MATERIAL_RESOURCES(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_1),
    ENTERED_IN_THE_REGISTER_OF_SPORTS_ACTIVITIES(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_1),
    IS_MEMBER_OF_THE_CITY_COUNTY_AND_NATIONAL_SPORTS_FEDERATION(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_2),
    PARTICIPATES_IN_THE_COMPETITION_SYSTEM_OF_NATIONAL_FEDERATION(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_2),
    COMMITTED_WORK_PLAN_AND_PROGRAM_FOR_NEXT_YEAR(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_2),
    COMMITTED_WORK_REPORT_FOR_PREVIOUS_YEAR(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_2),
    COMMITTED_FINANCIAL_PLAN_FOR_PREVIOUS_YEAR(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_2),
    EXTRACT_FROM_THE_REGISTER_OF_REPUBLIC_CROATIA(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    EXTRACT_FROM_COURT_REGISTER(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    COPY_OF_THE_STAMP_FOR_PROFESSIONAL_QUALIFICATION(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    COPY_OF_THE_MINUTES_FROM_LAST_ASSEMBLY(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    COPY_OF_NOTICE_OF_ENTRY_INTO_REGISTER_OF_SPORTS_ACTIVITIES(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    CERTIFICATE_OF_ENTRY_INTO_REGISTER_OF_NON_PROFIT_ORGANIZATIONS(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_1),
    PROGRAM_PROPOSAL_WITH_DETAILED_EXPLANATION(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_2),
    CERTIFICATE_OF_TRUTHFULNESS_AND_ACCURACY_OF_DATA(QuestionValueType.RADIO, QuestionGroup.GENERAL_CONDITIONS_DOCUMENTATION_2),
    PROFESSIONAL_ASSOCIATION_WHOSE_ACTIVITY_IS_NOT_SPORT_RELATED(QuestionValueType.RADIO, QuestionGroup.AUTOMATIC_DEACTIVATION_OF_A_MEMBER),
    AN_ASSOCIATION_THAT_IS_IN_BANKRUPTCY(QuestionValueType.RADIO, QuestionGroup.AUTOMATIC_DEACTIVATION_OF_A_MEMBER),
    AN_ASSOCIATION_THAT_IS_ONE_OF_FOUNDERS_OF_POLITICAL_PARTY(QuestionValueType.RADIO, QuestionGroup.AUTOMATIC_DEACTIVATION_OF_A_MEMBER);

    private final QuestionValueType type;
    private final QuestionGroup group;

    private EvaluationQuestionnaireDefEnum(QuestionValueType type, QuestionGroup group) {
        this.type = type;
        this.group = group;
    }

    public QuestionValueType getType() {
        return type;
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
