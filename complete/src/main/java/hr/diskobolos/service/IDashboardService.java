/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service;

import hr.diskobolos.dto.DashboardDto;

/**
 *
 * @author Tomislav Čavka
 */
public interface IDashboardService {

    DashboardDto fetchDashboardData();
}
