/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.converter;

import hr.diskobolos.dto.EvaluationDto;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef;

/**
 *
 * @author Tomislav Čavka
 */
public interface IEvaluationConverter {

    EvaluationDto convertEvaluationQuestionDefToEvaluationDto(EvaluationQuestionDef evaluationQuestionDef);
}
