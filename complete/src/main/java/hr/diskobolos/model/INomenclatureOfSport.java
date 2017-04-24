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
public interface INomenclatureOfSport extends IIdentifier {

    public NomenclatureCategories getCategory();

    public String getCategoryDescription();

    public String getValue();

    public Sport getSport();
}
