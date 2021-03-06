/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.diskobolos.dto.PhoneDto;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.Phone;
import hr.diskobolos.model.PhoneType;
import static hr.diskobolos.model.PhoneType.FAX;
import static hr.diskobolos.model.PhoneType.MOBILE;
import static hr.diskobolos.model.PhoneType.PHONE;
import hr.diskobolos.service.IBankAccountService;
import hr.diskobolos.service.IEmailService;
import hr.diskobolos.service.IMemberRegisterService;
import hr.diskobolos.service.IPhoneService;
import hr.diskobolos.util.ErrorHandlerUtils;
import hr.diskobolos.util.JSONMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    IPhoneService phoneService;

    @Autowired
    JSONMapper jsonMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String fetchAllMemberRegisters() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberRegister> memberRegisters = memberRegisterService.findAll();
        resultMap.put("memberRegisters", memberRegisters);
        return mapper.writeValueAsString(resultMap);
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
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String editMemberRegisterData(@RequestBody MemberRegister memberRegister, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            memberRegister.getEmails().stream().forEach((email) -> {
                email.setMemberRegister(memberRegister);
            });
            memberRegister.getBankAccounts().stream().forEach((bankAccounts) -> {
                bankAccounts.setMemberRegister(memberRegister);
            });

            List<Phone> phones = mapPhoneDtoToPhoneModelObject(memberRegister.getPhonesDto());
            phones.stream().forEach((phone) -> {
                phone.setMemberRegister(memberRegister);
            });
            memberRegister.setPhones(phones);

            memberRegisterService.update(memberRegister);

            if (memberRegister.getRemovedPhones() != null) {
                phoneService.delete(memberRegister.getRemovedPhones());
            }

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
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String createMemberRegisterData(@RequestBody MemberRegister memberRegister, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            memberRegister.getEmails().stream().forEach((email) -> {
                email.setMemberRegister(memberRegister);
            });
            memberRegister.getBankAccounts().stream().forEach((bankAccounts) -> {
                bankAccounts.setMemberRegister(memberRegister);
            });

            List<Phone> phones = mapPhoneDtoToPhoneModelObject(memberRegister.getPhonesDto());
            phones.stream().forEach((phone) -> {
                phone.setMemberRegister(memberRegister);
            });
            memberRegister.setPhones(phones);

            memberRegisterService.persist(memberRegister);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during creation of member register data: ", e.getLocalizedMessage());
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
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String deleteMemberRegisterData(@RequestBody MemberRegister memberRegister, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            memberRegisterService.delete(memberRegister);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    @RequestMapping(value = "/getMemberRegisterById/{memberRegisterId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String getMemberRegisterById(@PathVariable Integer memberRegisterId) {
        JSONObject resultMap = new JSONObject();
        MemberRegister memberRegister = memberRegisterService.findById(memberRegisterId);
        JSONObject memberRegisterObj = jsonMapper.getJSONObject(memberRegister);
        resultMap.put("memberRegister", memberRegisterObj);
        return resultMap.toString();
    }

    private List<Phone> mapPhoneDtoToPhoneModelObject(List<PhoneDto> phonesDto) {
        List<Phone> phones = new ArrayList<>();

        for (PhoneDto phoneDto : phonesDto) {

            if (phoneDto.getData() == null) {
                continue;
            }

            for (PhoneDto.Value data : phoneDto.getData()) {
                PhoneType phoneTypes = PhoneType.getInstance(phoneDto.getCategory());
                Phone phone = new Phone();
                switch (phoneTypes) {
                    case PHONE:
                        phone.setId(data.getId());
                        phone.setPhoneType(PHONE);
                        phone.setPhoneNumber(data.getText());
                        break;
                    case MOBILE:
                        phone.setId(data.getId());
                        phone.setPhoneType(MOBILE);
                        phone.setPhoneNumber(data.getText());
                        break;
                    case FAX:
                        phone.setId(data.getId());
                        phone.setPhoneType(FAX);
                        phone.setPhoneNumber(data.getText());
                        break;
                    default:
                        break;
                }
                phones.add(phone);
            }
        }
        return phones;
    }
}
