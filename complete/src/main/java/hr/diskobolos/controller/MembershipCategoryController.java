/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.model.MembershipCategory;
import hr.diskobolos.service.IMembershipCategoryService;
import hr.diskobolos.util.JSONMapper;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST services responsible for fetching, creation, edit and deletion of the membership category data
 * 
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/categories", produces = {"application/json; charset=utf-8"})
public class MembershipCategoryController {

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

}