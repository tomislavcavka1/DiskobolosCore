/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import java.io.Serializable;

/**
 * Objects can be identified by an unique identifier. How unique this identifier
 * is, depends on the context.
 *
 * @author Tomislav Čavka
 */
public interface IIdentifier extends Serializable {

    /**
     * Gets the identifier of this object
     *
     * @return id of this object
     */
    public Integer getId();
}
