/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.converter;

import hr.diskobolos.dto.TermsOfConditionDto;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.service.IEvaluationAnswerService;
import java.util.List;
import java.util.Locale;
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
        boolean isValid = evaluationAnswers.stream().allMatch(e -> e.getAnswer().getValue().equals(messageSource.getMessage("QuestionChoicesDef.yes", null, Locale.ENGLISH)));
        termsOfConditionDto.setIsValid(isValid && !evaluationAnswers.isEmpty());

        return termsOfConditionDto;
    }

}
