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
import hr.diskobolos.service.INomenclatureOfSportService;
import hr.diskobolos.service.ISportService;
import hr.diskobolos.util.ErrorHandlerUtils;
import hr.diskobolos.util.JSONMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    private static final Logger logger = LoggerFactory.getLogger(SportController.class);

    @Autowired
    ISportService sportService;

    @Autowired
    INomenclatureOfSportService nomenclatureOfSportService;

    @Autowired
    JSONMapper jsonMapper;

    @Autowired
    private MessageSource messageSource;

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
            Sport sport = mapSportDtoToSportModelObject(sportDto);
            sportService.update(sport);
            if (sportDto.getRemovedNomenclatureItems() != null) {
                nomenclatureOfSportService.delete(sportDto.getRemovedNomenclatureItems());
            }
            response.setStatus(HttpServletResponse.SC_OK);

            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during editing sport data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    /**
     * REST service responsible for creating sport data
     *
     * @param sportDto
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createSportData(@RequestBody SportDto sportDto, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            Sport sport = mapSportDtoToSportModelObject(sportDto);
            sportService.persist(sport);
            response.setStatus(HttpServletResponse.SC_OK);

            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            logger.error("Error during creation of sport data: ", e.getMessage());
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    /**
     * REST service responsible for deletion of the sport data
     *
     * @param sport
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteSportData(@RequestBody Sport sport, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            sportService.delete(sport);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject().put("result", 200).toString();
        } catch (Exception e) {
            return ErrorHandlerUtils.handleAjaxError(request, response);
        }
    }

    private Sport mapSportDtoToSportModelObject(SportDto sportDto) {
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
                        nomenclatureOfSport.setCategoryDescription(messageSource.getMessage(NATIONAL_SPORTS_FEDERATION.getLocalizationKey(), null, Locale.ENGLISH));
                        nomenclatureOfSport.setValue(data.getText());
                        break;
                    case INTERNATIONAL_FEDERATION:
                        nomenclatureOfSport.setId(data.getId());
                        nomenclatureOfSport.setCategory(INTERNATIONAL_FEDERATION);
                        nomenclatureOfSport.setCategoryDescription(messageSource.getMessage(INTERNATIONAL_FEDERATION.getLocalizationKey(), null, Locale.ENGLISH));
                        nomenclatureOfSport.setValue(data.getText());
                        break;
                    case IOC_SPORTACCORD:
                        nomenclatureOfSport.setId(data.getId());
                        nomenclatureOfSport.setCategory(IOC_SPORTACCORD);
                        nomenclatureOfSport.setCategoryDescription(messageSource.getMessage(IOC_SPORTACCORD.getLocalizationKey(), null, Locale.ENGLISH));
                        nomenclatureOfSport.setValue(data.getText());
                        break;
                    default:
                        break;
                }
                nomenclatureOfSports.add(nomenclatureOfSport);
            }
        }
        sport.setNomenclatureOfSports(nomenclatureOfSports);
        return sport;
    }
}
