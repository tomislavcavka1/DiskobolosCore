/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

/**
 *
 * @author Tomislav Čavka
 */
public enum NomenclatureCategories {

    // TODO: instead of description put localization key and fetch real value by using ResourceBundle
    NATIONAL_SPORTS_FEDERATION("NATIONAL_SPORTS_FEDERATION", "NACIONALNI SPORTSKI SAVEZ/članstvo u HOO-u"),
    INTERNATIONAL_FEDERATION("INTERNATIONAL_FEDERATION", "MEĐUNARODNA FEDERACIJA"),
    IOC_SPORTACCORD("IOC_SPORTACCORD", "Priznati od IOC-a, članovi SPORTACCORD-a");

    private final String type;
    private final String description;

    private NomenclatureCategories(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static NomenclatureCategories getInstance(String type) {
        for (NomenclatureCategories method : NomenclatureCategories.values()) {
            if (type.equals(method.type)) {
                return method;
            }
        }
        return null;
    }
}
