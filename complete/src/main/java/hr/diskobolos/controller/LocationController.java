/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.model.Location;
import hr.diskobolos.service.ILocationService;
import hr.diskobolos.util.JSONMapper;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST services responsible for fetching, creation, edit and deletion of the
 * location data
 *
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/locations", produces = {"application/json; charset=utf-8"})
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    ILocationService locationService;

    @Autowired
    JSONMapper jsonMapper;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String fetchAllLocations() {
        JSONObject resultMap = new JSONObject();
        List<Location> locations = locationService.findAll();
        JSONArray locationsJson = jsonMapper.getJSONArray(locations);
        resultMap.put("locations", locationsJson);
        return resultMap.toString();
    }
}
