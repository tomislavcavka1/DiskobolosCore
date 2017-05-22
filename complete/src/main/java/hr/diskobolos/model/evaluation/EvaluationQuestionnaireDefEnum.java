package hr.diskobolos.model.evaluation;

/**
 * Defines questionnaire for the evaluation module
 *
 * @author Tomislav Čavka
 */
public enum EvaluationQuestionnaireDefEnum {
    IS_REGISTERED(QuestionValueType.RADIO),
    IN_OPERATION_LEAST_TWO_YEARS(QuestionValueType.RADIO),
    HEADQUARTERS_IN_ZADAR(QuestionValueType.RADIO),
    APPLIES_TRANSPARENT_BUSINESS(QuestionValueType.RADIO),
    HAS_ENSURED_HUMAN_AND_MATERIAL_RESOURCES(QuestionValueType.RADIO),
    ENTERED_IN_THE_REGISTER_OF_SPORTS_ACTIVITIES(QuestionValueType.RADIO);
    
    private final QuestionValueType type;

    private EvaluationQuestionnaireDefEnum(QuestionValueType type) {
        this.type = type;
    }

    public QuestionValueType getType() {
        return type;
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
