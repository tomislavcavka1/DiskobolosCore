/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public interface IEvaluationAnswerPersistence extends IJpaDaoPersistence<EvaluationAnswer, Integer> {

    List<EvaluationAnswer> findAllByMemberRegister(MemberRegister memberRegister);
}
