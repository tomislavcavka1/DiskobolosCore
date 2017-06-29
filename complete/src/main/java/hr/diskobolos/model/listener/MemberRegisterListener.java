/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.listener;

import hr.diskobolos.model.Location;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.persistence.ILocationPersistence;
import hr.diskobolos.util.AutowireHelper;
import javax.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tomislav Čavka
 */
public class MemberRegisterListener {

    @Autowired
    ILocationPersistence locationPersistence;

    @PrePersist
    private void beforePersistOfMemberRegister(Object object) {
        AutowireHelper.autowire(this, this.locationPersistence);
        if (object instanceof MemberRegister) {
            MemberRegister memberRegister = ((MemberRegister) object);
            Location location = locationPersistence.findByAddress(memberRegister.getLocation().getAddress());
            if (location == null) {
                locationPersistence.persist(memberRegister.getLocation());
            }
            memberRegister.setLocation(location);
        }
    }
}
