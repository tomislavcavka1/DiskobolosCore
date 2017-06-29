/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

/**
 *
 * @author Tomislav Čavka
 */
public interface IDashboardDto {

    public enum CategorizationOfSportsGroup {

        CATEGORY_1(310, 500),
        CATEGORY_2(210, 300),
        CATEGORY_3(110, 200),
        CATEGORY_4(0, 100);

        private final int from;
        private final int to;

        private CategorizationOfSportsGroup(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public Integer getFrom() {
            return from;
        }

        public Integer getTo() {
            return to;
        }
    }

}
