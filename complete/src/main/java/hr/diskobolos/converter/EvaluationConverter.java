/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.converter;

import hr.diskobolos.dto.EvaluationDto;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.model.evaluation.QuestionChoicesDef;
import java.util.ArrayList;
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
public class EvaluationConverter implements IEvaluationConverter {
    
    @Autowired
    private MessageSource messageSource;
    
    @Override
    public EvaluationDto convertEvaluationQuestionDefToEvaluationDto(EvaluationQuestionDef evaluationQuestionDef) {
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluationDto.setId(evaluationQuestionDef.getId());
        evaluationDto.setQuestion(evaluationQuestionDef.getQuestion().getName());
        evaluationDto.setLabel(messageSource.getMessage(evaluationQuestionDef.getQuestion().getLocalizationKey(), null, Locale.ENGLISH));
        List<EvaluationDto.Item> items = new ArrayList<>();
        for (QuestionChoicesDef choice : evaluationQuestionDef.getChoices()) {
            EvaluationDto.Item item = new EvaluationDto.Item();
            item.setId(choice.getId());
            item.setValue(messageSource.getMessage(evaluationQuestionDef.getQuestion().getLocalizationKey().concat(".").concat(choice.getValue()), null, Locale.ENGLISH));
            items.add(item);            
        }
        evaluationDto.setItems(items);        
        return evaluationDto;
    }
}
