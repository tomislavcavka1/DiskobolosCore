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

    NATIONAL_SPORTS_FEDERATION,
    INTERNATIONAL_FEDERATION,
    IOC_SPORTACCORD;

    private NomenclatureCategories() {
    }

    public String getName() {
        return this.name();
    }

    public String getLocalizationKey() {
        return this.getClass().getSimpleName() + "." + this.name();
    }

    public static NomenclatureCategories getInstance(String type) {
        for (NomenclatureCategories method : NomenclatureCategories.values()) {
            if (type.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }
}
