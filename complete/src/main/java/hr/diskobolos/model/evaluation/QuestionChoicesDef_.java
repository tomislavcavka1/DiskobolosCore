/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Tomislav Čavka
 */
public class QuestionChoicesDef_ {

    public static volatile SingularAttribute<QuestionChoicesDef, Integer> id;
    public static volatile SingularAttribute<QuestionChoicesDef, String> label;
    public static volatile SingularAttribute<QuestionChoicesDef, String> value;
    public static volatile SingularAttribute<QuestionChoicesDef, QuestionValueType> valueType;
    public static volatile SingularAttribute<QuestionChoicesDef, EvaluationQuestionDef> evaluationQuestionDef;
}
