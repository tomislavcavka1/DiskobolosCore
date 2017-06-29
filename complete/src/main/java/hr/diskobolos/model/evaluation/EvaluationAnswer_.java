/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

import hr.diskobolos.model.MemberRegister;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Tomislav Čavka
 */
@StaticMetamodel(EvaluationAnswer.class)
public class EvaluationAnswer_ {

    public static volatile SingularAttribute<EvaluationAnswer, Integer> id;
    public static volatile SingularAttribute<EvaluationAnswer, MemberRegister> memberRegister;
    public static volatile SingularAttribute<EvaluationAnswer, QuestionChoicesDef> answer;
}
