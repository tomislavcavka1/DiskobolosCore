/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service;

import hr.diskobolos.dto.CategorizationOfSportsPerSportClubDto;
import hr.diskobolos.dto.RankingAndCategorizationOfSportsDto;
import hr.diskobolos.dto.TermsOfCompetitionDto;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public interface IEvaluationAnswerService extends IJpaDaoService<EvaluationAnswer, Integer> {

    List<EvaluationAnswer> findAllByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType);

    TermsOfCompetitionDto fetchTermsOfCompetitionByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType);

    RankingAndCategorizationOfSportsDto fetchRankingAndCategorizationOfSportsByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType);

    CategorizationOfSportsPerSportClubDto fetchCategorizationOfSportsPerSportClubByMemberRegisterAndQuestionnaireType(MemberRegister memberRegister, QuestionnaireType questionnaireType);
}
