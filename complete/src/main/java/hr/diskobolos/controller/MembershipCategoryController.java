/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.model.MembershipCategory;
import hr.diskobolos.service.IMembershipCategoryService;
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
 * membership category data
 *
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/categories", produces = {"application/json; charset=utf-8"})
public class MembershipCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(MembershipCategoryController.class);

    @Autowired
    IMembershipCategoryService membershipCategoryService;

    @Autowired
    JSONMapper jsonMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String fetchAllMembershipCategories() {
        JSONObject resultMap = new JSONObject();
        List<MembershipCategory> membershipCategories = membershipCategoryService.findAll();
        JSONArray membershipCategoriesJson = jsonMapper.getJSONArray(membershipCategories);
        resultMap.put("membershipCategories", membershipCategoriesJson);
        return resultMap.toString();
    }

    /**
     * REST service responsible for editing membership category data
     *
     * @param membershipCategory
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editMembershipCategorysData(@RequestBody MembershipCategory membershipCategory, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            membershipCategoryService.update(membershipCategory);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during editing membership category data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    /**
     * REST service responsible for creation of membership category data
     *
     * @param membershipCategory
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createMembershipCategorysData(@RequestBody MembershipCategory membershipCategory, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            membershipCategoryService.persist(membershipCategory);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during creation of membership category data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    /**
     * REST service responsible for deletion of the membership category data
     *
     * @param membershipCategory
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteSportData(@RequestBody MembershipCategory membershipCategory, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            membershipCategoryService.delete(membershipCategory);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }
}
