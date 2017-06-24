/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto.leaflet;

/**
 *
 * @author Tomislav Čavka
 */
public interface IGeoJsonDto {
    
    public enum GeoFeatureType {
        FEATURE_COLLECTION(1, "FeatureCollection"),
        FEATURE(2, "Feature"),
        POINT(3, "Point");
 
        private final int id;
        private final String description;

        private GeoFeatureType(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }
        
        public String getDescription() {
            return description;
        }

        public static GeoFeatureType getInstance(int id) {
            for (GeoFeatureType method : GeoFeatureType.values()) {
                if (id == method.id) {
                    return method;
                }
            }
            return null;
        }
    }
    
}
