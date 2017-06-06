/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.converter.IConverter;
import hr.diskobolos.dto.EvaluationAnswerDto;
import hr.diskobolos.dto.EvaluationDto;
import hr.diskobolos.dto.TermsOfConditionDto;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.EvaluationAnswer;
import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.service.IEvaluationAnswerService;
import hr.diskobolos.service.IEvaluationQuestionDefService;
import hr.diskobolos.service.IMemberRegisterService;
import hr.diskobolos.util.ErrorHandlerUtils;
import hr.diskobolos.util.JSONMapper;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Autowired
    IConverter<MemberRegister, TermsOfConditionDto> evaluationAnswerConverter;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
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

    @RequestMapping(value = "/fetchMemberRegistersWithAssociatedEvaluations", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String fetchMemberRegistersWithAssociatedEvaluations() {
        JSONObject resultMap = new JSONObject();
        List<MemberRegister> memberRegisters = memberRegisterService.findAll();
        List<TermsOfConditionDto> termsOfConditionDto = memberRegisters.stream()
                .map(m -> evaluationAnswerConverter.convert(m))
                .collect(Collectors.toList());
        JSONArray termsOfConditionDtoJson = jsonMapper.getJSONArray(termsOfConditionDto);
        resultMap.put("termsOfConditionDtoJson", termsOfConditionDtoJson);
        return resultMap.toString();
    }

    @RequestMapping(value = "/fetchEvaluationAnswersByMemberRegisterId/{memberRegisterId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String fetchEvaluationAnswersByMemberRegisterId(@PathVariable Integer memberRegisterId) {
        JSONObject resultMap = new JSONObject();
        MemberRegister memberRegister = memberRegisterService.findById(memberRegisterId);
        List<EvaluationAnswer> evaluationAnswers = evaluationAnswerService.findAllByMemberRegister(memberRegister);

        evaluationAnswers.forEach((EvaluationAnswer e) -> {
            e.getAnswer().setValue(messageSource.getMessage(e.getClass().getSimpleName().concat(".").concat(e.getAnswer().getValue()), null, Locale.ENGLISH));
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
