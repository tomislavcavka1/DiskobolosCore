/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence;

import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public interface IEvaluationQuestionDefPersistence extends IJpaDaoPersistence<EvaluationQuestionDef, Integer> {

    List<EvaluationQuestionDef> findByQuestionnanireType(QuestionnaireType questionnaireType);
}
