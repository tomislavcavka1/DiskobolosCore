/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.config.security.authentication;

import org.springframework.security.core.GrantedAuthority;

/**
 * Interface that exposes custom properties for the authorized user
 *
 * @author Tomislav Čavka
 */
public interface ICustomGrantedAuthority extends GrantedAuthority {

    Integer getPermissionLevel();
}
