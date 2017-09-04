/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service;

import hr.diskobolos.model.security.User;

/**
 *
 * @author Tomislav Čavka
 */
public interface IUserService extends IJpaDaoService<User, Integer> {

    User findByUsername(String username);

    void postAuthorizationUpdate(Integer id);

    void preLogoutActions(Integer id);
}
