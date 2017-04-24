/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.controller;

import hr.diskobolos.dto.NomenclatureOfSportsDto;
import hr.diskobolos.dto.SportDto;
import hr.diskobolos.model.NomenclatureCategories;
import static hr.diskobolos.model.NomenclatureCategories.*;
import hr.diskobolos.model.NomenclatureOfSport;
import hr.diskobolos.model.Sport;
import hr.diskobolos.service.ISportService;
import hr.diskobolos.util.JSONMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST services responsible for fetching, creation, edit and deletion of the
 * sport data
 *
 * @author Tomislav Čavka
 */
@RestController
@RequestMapping(value = "/sports", produces = {"application/json; charset=utf-8"})
public class SportController {

    @Autowired
    ISportService sportService;

    @Autowired
    JSONMapper jsonMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String fetchAllSports() {
        JSONObject resultMap = new JSONObject();
        List<Sport> sports = sportService.findAll();
        JSONArray sportsJson = jsonMapper.getJSONArray(sports);
        resultMap.put("sports", sportsJson);
        return resultMap.toString();
    }

    /**
     * REST service responsible for editing sport data
     *
     * @param sportDto
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editSportData(@RequestBody SportDto sportDto, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            Sport sport = new Sport();
            sport.setId(sportDto.getId());
            sport.setName(sportDto.getName());

            List<NomenclatureOfSport> nomenclatureOfSports = new ArrayList<>();

            for (NomenclatureOfSportsDto nomenclatureOfSportsDto : sportDto.getNomenclatureOfSports()) {

                for (NomenclatureOfSportsDto.Value data : nomenclatureOfSportsDto.getData()) {
                    NomenclatureCategories nomenclatureCategories = NomenclatureCategories.getInstance(nomenclatureOfSportsDto.getCategory());
                    NomenclatureOfSport nomenclatureOfSport = new NomenclatureOfSport();
                    nomenclatureOfSport.setSport(sport);
                    switch (nomenclatureCategories) {
                        case NATIONAL_SPORTS_FEDERATION:
                            nomenclatureOfSport.setId(data.getId());
                            nomenclatureOfSport.setCategory(NATIONAL_SPORTS_FEDERATION);
                            nomenclatureOfSport.setCategoryDescription(NATIONAL_SPORTS_FEDERATION.getDescription());
                            nomenclatureOfSport.setValue(data.getText());
                            break;
                        case INTERNATIONAL_FEDERATION:
                            nomenclatureOfSport.setId(data.getId());
                            nomenclatureOfSport.setCategory(INTERNATIONAL_FEDERATION);
                            nomenclatureOfSport.setCategoryDescription(INTERNATIONAL_FEDERATION.getDescription());
                            nomenclatureOfSport.setValue(data.getText());
                            break;
                        case IOC_SPORTACCORD:
                            nomenclatureOfSport.setId(data.getId());
                            nomenclatureOfSport.setCategory(IOC_SPORTACCORD);
                            nomenclatureOfSport.setCategoryDescription(IOC_SPORTACCORD.getDescription());
                            nomenclatureOfSport.setValue(data.getText());
                            break;
                        default:
                            break;
                    }
                    nomenclatureOfSports.add(nomenclatureOfSport);
                }
            }
            sport.setNomenclatureOfSports(nomenclatureOfSports);
            sportService.update(sport);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
//            return ErrorHandlerUtils.handleAjaxError(request, response);
            return null;
        }
    }
}
