/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

import hr.diskobolos.model.FinancialResources;
import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public class FinancialResourcesDto {

    private List<FinancialResources> financialResources;

    public List<FinancialResources> getFinancialResources() {
        return financialResources;
    }

    public void setFinancialResources(List<FinancialResources> financialResources) {
        this.financialResources = financialResources;
    }

}
