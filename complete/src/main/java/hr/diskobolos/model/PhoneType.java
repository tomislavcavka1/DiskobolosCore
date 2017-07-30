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
public enum PhoneType {
    PHONE,
    FAX,
    MOBILE;

    private PhoneType() {
    }

    public String getName() {
        return this.name();
    }

    public String getLocalizationKey() {
        return this.getClass().getSimpleName() + "." + this.name();
    }

    public static PhoneType getInstance(String type) {
        for (PhoneType method : PhoneType.values()) {
            if (type.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }
}
