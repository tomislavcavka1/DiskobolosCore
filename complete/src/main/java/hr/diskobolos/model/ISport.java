/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public interface ISport extends IIdentifier {

    public String getName();

    public List<NomenclatureOfSport> getNomenclatureOfSports();

}
