/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.service.IBankAccountService;
import hr.diskobolos.service.IEmailService;
import hr.diskobolos.service.IMemberRegisterService;
import hr.diskobolos.util.ErrorHandlerUtils;
import hr.diskobolos.util.JSONMapper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST services responsible for fetching, creation, edit and deletion of the
 * member register data
 *
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/memberRegister", produces = {"application/json; charset=utf-8"})
public class MemberRegisterController {

    private static final Logger logger = LoggerFactory.getLogger(MemberRegisterController.class);

    @Autowired
    IMemberRegisterService memberRegisterService;

    @Autowired
    IEmailService emailService;

    @Autowired
    IBankAccountService bankAccountService;

    @Autowired
    JSONMapper jsonMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String fetchAllMemberRegisters() {
        JSONObject resultMap = new JSONObject();
        List<MemberRegister> memberRegisters = memberRegisterService.findAll();
        JSONArray memberRegistersJson = jsonMapper.getJSONArray(memberRegisters);
        resultMap.put("memberRegisters", memberRegistersJson);
        return resultMap.toString();
    }

    /**
     * REST service responsible for editing member register data
     *
     * @param memberRegister
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editMemberRegisterData(@RequestBody MemberRegister memberRegister, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            memberRegister.getEmails().stream().forEach((email) -> {
                email.setMemberRegister(memberRegister);
            });
            memberRegister.getBankAccounts().stream().forEach((bankAccounts) -> {
                bankAccounts.setMemberRegister(memberRegister);
            });

            memberRegisterService.update(memberRegister);

            if (memberRegister.getRemovedEmails() != null) {
                emailService.delete(memberRegister.getRemovedEmails());
            }

            if (memberRegister.getRemovedBankAccounts() != null) {
                bankAccountService.delete(memberRegister.getRemovedBankAccounts());
            }

            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during editing member register data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    /**
     * REST service responsible for creating member register data
     *
     * @param memberRegister
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createMemberRegisterData(@RequestBody MemberRegister memberRegister, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            memberRegister.getEmails().stream().forEach((email) -> {
                email.setMemberRegister(memberRegister);
            });
            memberRegister.getBankAccounts().stream().forEach((bankAccounts) -> {
                bankAccounts.setMemberRegister(memberRegister);
            });

            memberRegisterService.persist(memberRegister);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during creation of member register data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    /**
     * REST service responsible for deletion of the member register data
     *
     * @param memberRegister
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteMemberRegisterData(@RequestBody MemberRegister memberRegister, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            memberRegisterService.delete(memberRegister);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

}
