/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.dto.FinancialResourcesDto;
import hr.diskobolos.model.FinancialResources;
import hr.diskobolos.service.IFinancialResourcesService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST services responsible for fetching, creation, edit and deletion of the
 * financial resources data
 *
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/financialResources", produces = {"application/json; charset=utf-8"})
public class FinancialResourcesController {

    private static final Logger logger = LoggerFactory.getLogger(SportController.class);

    @Autowired
    IFinancialResourcesService financialResourcesService;

    @Autowired
    JSONMapper jsonMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String fetchAllFinancialResources() {
        JSONObject resultMap = new JSONObject();
        List<FinancialResources> financialResources = financialResourcesService.findAll();
        JSONArray financialResourcesJson = jsonMapper.getJSONArray(financialResources);
        resultMap.put("financialResources", financialResourcesJson);
        return resultMap.toString();
    }

    /**
     * REST service responsible for editing financial resources data
     *
     * @param financialResourcesDto
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String editFinancialResourcesData(@RequestBody FinancialResourcesDto financialResourcesDto, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            financialResourcesService.bulkSave(financialResourcesDto.getFinancialResources());
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during editing financial resources data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

}
