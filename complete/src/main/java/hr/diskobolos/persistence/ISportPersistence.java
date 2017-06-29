/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence;

import hr.diskobolos.model.Sport;

/**
 *
 * @author Tomislav Čavka
 */
public interface ISportPersistence extends IJpaDaoPersistence<Sport, Integer> {

    Long getNumberOfSports();
}
