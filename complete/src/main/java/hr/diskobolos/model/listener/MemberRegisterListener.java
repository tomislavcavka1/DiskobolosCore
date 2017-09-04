/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.listener;

import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.MembershipCategory;
import hr.diskobolos.model.Sport;
import hr.diskobolos.service.IMembershipCategoryService;
import hr.diskobolos.service.ISportService;
import hr.diskobolos.util.AutowireHelper;
import java.util.Locale;
import javax.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @author Tomislav Čavka
 */
public class MemberRegisterListener {

    private final String UNKNOWN = "UNKNOWN";

    @Autowired
    IMembershipCategoryService membershipCategoryService;

    @Autowired
    ISportService sportService;

    @Autowired
    private MessageSource messageSource;

    @PrePersist
    private void beforePersistOfMemberRegister(Object object) {
        AutowireHelper.autowire(this, this.membershipCategoryService, this.sportService, this.messageSource);
        if (object instanceof MemberRegister) {
            MemberRegister memberRegister = (MemberRegister) object;

            if (memberRegister.getMembershipCategory().getId() == null) {
                MembershipCategory membershipCategory = membershipCategoryService.findMembershipCategoryByDescription(messageSource.getMessage(UNKNOWN, null, Locale.ENGLISH));
                memberRegister.setMembershipCategory(membershipCategory);
            }

            if (memberRegister.getSportCategory().getId() == null) {
                Sport sport = sportService.findSportByName(messageSource.getMessage(UNKNOWN, null, Locale.ENGLISH));
                memberRegister.setSportCategory(sport);
            }
        }
    }

}
