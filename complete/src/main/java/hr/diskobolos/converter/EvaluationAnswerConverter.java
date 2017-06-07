/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.converter;

import hr.diskobolos.dto.TermsOfConditionDto;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.EvaluationQuestionnaireDefEnum;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.model.evaluation.TermsOfConditionStatus;
import hr.diskobolos.service.IEvaluationAnswerService;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tomislav Čavka
 */
@Component
public class EvaluationAnswerConverter implements IConverter<MemberRegister, TermsOfConditionDto> {

    @Autowired
    IEvaluationAnswerService evaluationAnswerService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public TermsOfConditionDto convert(MemberRegister memberRegister) {
        TermsOfConditionDto termsOfConditionDto = new TermsOfConditionDto();
        termsOfConditionDto.setId(memberRegister.getId());
        termsOfConditionDto.setName(memberRegister.getName());
        termsOfConditionDto.setAddress(memberRegister.getAddress());
        termsOfConditionDto.setRegisterNumber(memberRegister.getRegisterNumber());
        termsOfConditionDto.setRegistrationDate(memberRegister.getRegistrationDate());

        List<EvaluationAnswer> evaluationAnswers = evaluationAnswerService.findAllByMemberRegister(memberRegister);

        List<EvaluationQuestionnaireDefEnum> questionnaireDef = Arrays.asList(EvaluationQuestionnaireDefEnum.values());
        Long numberOfQuestion = questionnaireDef.stream().filter(q -> q.getQuestionnaireType().equals(QuestionnaireType.TERMS_OF_CONDITION)).collect(Collectors.counting());

        TermsOfConditionStatus termsOfConditionStatus = TermsOfConditionStatus.NONE;
        if (!evaluationAnswers.isEmpty() && evaluationAnswers.size() == numberOfQuestion) {
            boolean isValid = evaluationAnswers.stream().allMatch(e -> e.getAnswer().getValue().equals(messageSource.getMessage("QuestionChoicesDef.yes", null, Locale.ENGLISH)));
            termsOfConditionStatus = isValid ? TermsOfConditionStatus.VALID : TermsOfConditionStatus.INVALID;
        }
        termsOfConditionDto.setTermsOfConditionStatus(termsOfConditionStatus.name());
        return termsOfConditionDto;
    }

}
