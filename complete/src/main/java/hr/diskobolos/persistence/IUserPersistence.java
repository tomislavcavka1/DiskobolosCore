/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.persistence;

import hr.diskobolos.model.security.User;

/**
 *
 * @author Tomislav Čavka
 */
public interface IUserPersistence extends IJpaDaoPersistence<User, Integer> {

    User findByUsername(String username);
}
