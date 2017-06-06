/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

import hr.diskobolos.model.evaluation.EvaluationAnswer;
import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public class EvaluationAnswerDto {

    private List<EvaluationAnswer> evaluationAnswers;

    public List<EvaluationAnswer> getEvaluationAnswers() {
        return evaluationAnswers;
    }

    public void setEvaluationAnswers(List<EvaluationAnswer> evaluationAnswers) {
        this.evaluationAnswers = evaluationAnswers;
    }
}
