/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service;

import hr.diskobolos.dto.leaflet.GeoJsonDto;
import hr.diskobolos.model.Location;

/**
 *
 * @author Tomislav Čavka
 */
public interface ILocationService extends IJpaDaoService<Location, Integer> {
    
    GeoJsonDto fetchGeographicalData();
}
