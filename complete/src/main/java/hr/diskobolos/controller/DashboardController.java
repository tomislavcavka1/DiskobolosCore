/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.dto.DashboardDto;
import hr.diskobolos.service.IDashboardService;
import hr.diskobolos.util.JSONMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST services responsible for handling dashboard data
 *
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/dashboard", produces = {"application/json; charset=utf-8"})
public class DashboardController {

    @Autowired
    IDashboardService dashboardService;

    @Autowired
    JSONMapper jsonMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String fetchDashboardData() {
        JSONObject resultMap = new JSONObject();
        DashboardDto dashboardDto = dashboardService.fetchDashboardData();
        JSONObject dashboardJson = jsonMapper.getJSONObject(dashboardDto);
        resultMap.put("dashboardJson", dashboardJson);
        return resultMap.toString();
    }
}
