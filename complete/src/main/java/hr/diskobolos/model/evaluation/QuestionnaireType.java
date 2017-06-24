/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

/**
 *
 * @author Tomislav Čavka
 */
public enum QuestionnaireType {
    TERMS_OF_CONDITION,
    RANKING_AND_CATEGORIZATION_OF_SPORTS,
    CATEGORIZATION_OF_SPORTS_PER_SPORT_CLUB;

    public String getName() {
        return this.name();
    }

    public static QuestionnaireType getInstance(String type) {
        for (QuestionnaireType method : QuestionnaireType.values()) {
            if (type.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }
}
