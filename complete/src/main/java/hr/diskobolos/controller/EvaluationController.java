/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.converter.IConverter;
import hr.diskobolos.dto.EvaluationAnswerDto;
import hr.diskobolos.dto.EvaluationDto;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.service.IEvaluationAnswerService;
import hr.diskobolos.service.IEvaluationQuestionDefService;
import hr.diskobolos.service.IMemberRegisterService;
import hr.diskobolos.util.ErrorHandlerUtils;
import hr.diskobolos.util.JSONMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    IEvaluationAnswerService evaluationAnswerService;

    @Autowired
    IMemberRegisterService memberRegisterService;

    @Autowired
    JSONMapper jsonMapper;

    @Autowired
    IConverter<EvaluationQuestionDef, EvaluationDto> evaluationConverter;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String fetchAllEvaluationQuestions() {
        JSONObject resultMap = new JSONObject();
        List<EvaluationQuestionDef> evaluationQuestions = evaluationQuestionDefService.findAll();
        List<EvaluationDto> evaluationDtoQuestions = evaluationQuestions.stream()
                .map(e -> evaluationConverter.convert(e))
                .collect(Collectors.toList());
        JSONArray evaluationDtoQuestionsJson = jsonMapper.getJSONArray(evaluationDtoQuestions);
        resultMap.put("evaluationDtoQuestions", evaluationDtoQuestionsJson);
        return resultMap.toString();
    }

    @RequestMapping(value = "/findAllByQuestionnaireType/{questionnaireType}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String findAllByQuestionnaireType(@PathVariable String questionnaireType) {
        JSONObject resultMap = new JSONObject();
        List<EvaluationQuestionDef> evaluationQuestions = evaluationQuestionDefService.findByQuestionnanireType(QuestionnaireType.getInstance(questionnaireType));
        List<EvaluationDto> evaluationDtoQuestions = evaluationQuestions.stream()
                .map(e -> evaluationConverter.convert(e))
                .collect(Collectors.toList());
        JSONArray evaluationDtoQuestionsJson = jsonMapper.getJSONArray(evaluationDtoQuestions);
        resultMap.put("evaluationDtoQuestions", evaluationDtoQuestionsJson);
        return resultMap.toString();
    }

    /**
     * REST service responsible for creation of evaluation answers
     *
     * @param evaluationAnswerDto
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String createEvaluationAnswers(@RequestBody EvaluationAnswerDto evaluationAnswerDto, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            evaluationAnswerService.bulkSave(evaluationAnswerDto.getEvaluationAnswers());
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during editing member register data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    @RequestMapping(value = "/fetchMemberRegistersWithAssociatedEvaluations/{questionnaireType}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String fetchMemberRegistersWithAssociatedEvaluations(@PathVariable String questionnaireType) {
        JSONObject resultMap = new JSONObject();
        List<?> evaluationQuestionDefDto = null;
        List<MemberRegister> memberRegisters = memberRegisterService.findAll();
        switch (QuestionnaireType.getInstance(questionnaireType)) {
            case TERMS_OF_CONDITION:
                evaluationQuestionDefDto = memberRegisters.stream()
                        .map(m -> evaluationAnswerService.fetchTermsOfCompetitionByMemberRegisterAndQuestionnaireType(m, QuestionnaireType.TERMS_OF_CONDITION))
                        .collect(Collectors.toList());
                break;
            case RANKING_AND_CATEGORIZATION_OF_SPORTS:
                evaluationQuestionDefDto = memberRegisters.stream()
                        .map(m -> evaluationAnswerService.fetchRankingAndCategorizationOfSportsByMemberRegisterAndQuestionnaireType(m, QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS))
                        .collect(Collectors.toList());
                break;
            case CATEGORIZATION_OF_SPORTS_PER_SPORT_CLUB:
                evaluationQuestionDefDto = memberRegisters.stream()
                        .map(m -> evaluationAnswerService.fetchCategorizationOfSportsPerSportClubByMemberRegisterAndQuestionnaireType(m, QuestionnaireType.CATEGORIZATION_OF_SPORTS_PER_SPORT_CLUB))
                        .collect(Collectors.toList());
                break;
            default:
                break;
        }
        JSONArray evaluationQuestionDefJson = jsonMapper.getJSONArray(evaluationQuestionDefDto);
        resultMap.put("evaluationQuestionDefJson", evaluationQuestionDefJson);
        return resultMap.toString();
    }

    @RequestMapping(value = "/findAllByMemberRegisterAndQuestionnaireType/{memberRegisterId}/{questionnaireType}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String findAllByMemberRegisterAndQuestionnaireType(@PathVariable Integer memberRegisterId, @PathVariable String questionnaireType) {
        JSONObject resultMap = new JSONObject();
        MemberRegister memberRegister = memberRegisterService.findById(memberRegisterId);
        List<EvaluationAnswer> evaluationAnswers = evaluationAnswerService.findAllByMemberRegisterAndQuestionnaireType(memberRegister, QuestionnaireType.getInstance(questionnaireType));

        evaluationAnswers.forEach((EvaluationAnswer e) -> {
            e.getAnswer().setValue(e.getAnswer().getValue());
        });

        JSONArray evaluationAnswersJson = jsonMapper.getJSONArray(evaluationAnswers);
        resultMap.put("evaluationAnswersJson", evaluationAnswersJson);
        return resultMap.toString();
    }

    /**
     * REST service responsible for editing of the evaluation answers
     *
     * @param evaluationAnswerDto
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String editMemberRegisterData(@RequestBody EvaluationAnswerDto evaluationAnswerDto, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            evaluationAnswerService.bulkSave(evaluationAnswerDto.getEvaluationAnswers());
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during editing member register data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }
}
