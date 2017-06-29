/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Tomislav Čavka
 */
public class EvaluationQuestionDef_ {

    public static volatile SingularAttribute<EvaluationQuestionDef, Integer> id;
    public static volatile SingularAttribute<EvaluationQuestionDef, EvaluationQuestionnaireDefEnum> question;
    public static volatile SingularAttribute<EvaluationQuestionDef, QuestionInputType> valueType;
    public static volatile SingularAttribute<EvaluationQuestionDef, QuestionnaireType> questionnaireType;
    public static volatile SingularAttribute<EvaluationQuestionDef, Boolean> mandatory;
    public static volatile SingularAttribute<EvaluationQuestionDef, String> defaultValue;
    public static volatile ListAttribute<EvaluationQuestionDef, QuestionChoicesDef> choices;
}
