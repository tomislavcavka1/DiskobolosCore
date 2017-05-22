/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.converter.IEvaluationConverter;
import hr.diskobolos.dto.EvaluationDto;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.service.IEvaluationQuestionDefService;
import hr.diskobolos.util.JSONMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST services responsible for fetching, creation, edit and deletion of the
 * evaluation data
 *
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/evaluation", produces = {"application/json; charset=utf-8"})
public class EvaluationController {

    private static final Logger logger = LoggerFactory.getLogger(EvaluationController.class);

    @Autowired
    IEvaluationQuestionDefService evaluationQuestionDefService;

    @Autowired
    JSONMapper jsonMapper;    
    
    @Autowired
    IEvaluationConverter evaluationConverter;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String fetchAllEvaluationQuestions() {
        JSONObject resultMap = new JSONObject();
        List<EvaluationQuestionDef> evaluationQuestions = evaluationQuestionDefService.findAll();
        List<EvaluationDto> evaluationDtoQuestions = evaluationQuestions.stream()
                .map(e -> evaluationConverter.convertEvaluationQuestionDefToEvaluationDto(e))
                .collect(Collectors.toList());        
        JSONArray evaluationDtoQuestionsJson = jsonMapper.getJSONArray(evaluationDtoQuestions);
        resultMap.put("evaluationDtoQuestions", evaluationDtoQuestionsJson);
        return resultMap.toString();
    }
}
