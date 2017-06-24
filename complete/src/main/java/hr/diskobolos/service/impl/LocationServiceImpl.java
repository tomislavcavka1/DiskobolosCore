/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.dto.leaflet.Feature;
import hr.diskobolos.dto.leaflet.GeoJsonDto;
import hr.diskobolos.dto.leaflet.Geometry;
import hr.diskobolos.dto.leaflet.IGeoJsonDto;
import hr.diskobolos.dto.leaflet.Properties;
import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.model.Location;
import hr.diskobolos.persistence.ILocationPersistence;
import hr.diskobolos.service.ILocationService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class LocationServiceImpl implements ILocationService {

    @Autowired
    ILocationPersistence locationPersistence;

    @Override
    public void persist(Location entity) {
        locationPersistence.persist(entity);
    }

    @Override
    public void update(Location entity) {
        locationPersistence.update(entity);
    }

    @Override
    public <T extends IIdentifier> Collection<T> bulkSave(Collection<T> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends IIdentifier> T save(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Location entity) {
        locationPersistence.delete(entity);
    }

    @Override
    public void delete(List<Location> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> findAll() {
        return locationPersistence.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeoJsonDto fetchGeographicalData() {
        List<Location> locations = locationPersistence.findAll();
        GeoJsonDto geoJsonDto = null;
        List<Feature> features = new ArrayList<>();
        for (Location location : locations) {
            geoJsonDto = new GeoJsonDto();
            geoJsonDto.setType(IGeoJsonDto.GeoFeatureType.FEATURE_COLLECTION.getDescription());
            Feature feature = new Feature();
            feature.setType(IGeoJsonDto.GeoFeatureType.FEATURE.getDescription());
            feature.setId(String.valueOf(location.getId()));
            Properties properties = new Properties();
            properties.setName(location.getAddress());
            feature.setProperties(properties);
            Geometry geometry = new Geometry();
            geometry.setType(IGeoJsonDto.GeoFeatureType.POINT.getDescription());
            float[] coordinates = {location.getLongitude(), location.getLatitude()};
            geometry.setCoordinates(coordinates);
            feature.setGeometry(geometry);
            feature.setLocation(location);
            features.add(feature);
            geoJsonDto.setFeatures(features);
        }
        return geoJsonDto;
    }

}
