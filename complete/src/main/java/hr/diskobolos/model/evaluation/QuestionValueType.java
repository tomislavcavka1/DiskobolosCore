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
public enum QuestionValueType {
    Boolean(Boolean.class),
    Integer(Integer.class);

    private final Class<?> dataType;

    private QuestionValueType(Class<?> dataType) {
        this.dataType = dataType;
    }

    public Class<?> getDataType() {
        return dataType;
    }
}
