/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.config.security.authentication;

import java.util.Objects;

/**
 * Class that provides custom properties for the authorized user
 *
 * @author Tomislav Čavka
 */
public final class CustomGrantedAuthority implements ICustomGrantedAuthority {

    private final String role;
    private final Integer permissionLevel;

    public CustomGrantedAuthority(String role, Integer permissionLevel) {
        this.role = role;
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomGrantedAuthority other = (CustomGrantedAuthority) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.permissionLevel, other.permissionLevel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomGrantedAuthority{" + "role=" + role + ", permissionLevel=" + permissionLevel + '}';
    }
}
