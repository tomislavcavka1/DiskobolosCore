/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence;

import hr.diskobolos.model.Location;

/**
 * Main repository methods for location
 *
 * @author Tomislav Čavka
 */
public interface ILocationPersistence extends IJpaDaoPersistence<Location, Integer> {

    Location findByAddress(String address);
}
