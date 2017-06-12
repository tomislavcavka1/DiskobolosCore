/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

import hr.diskobolos.model.IIdentifier;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "QUESTION_CHOICES_DEF", schema = "DISKOBOLOS")
@NamedQuery(name = "QuestionChoicesDef.findAll", query = "SELECT q FROM QuestionChoicesDef q")
public class QuestionChoicesDef implements IIdentifier, Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.question_choices_def_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.question_choices_def_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.question_choices_def_id_seq")
    private Integer id;

    private String label;

    private String value;
    
    @Enumerated(EnumType.STRING)
    @Column(name="VALUE_TYPE")
    private QuestionValueType valueType;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION")
    private EvaluationQuestionDef evaluationQuestionDef;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QuestionValueType getValueType() {
        return valueType;
    }

    public void setValueType(QuestionValueType valueType) {
        this.valueType = valueType;
    }
    
    public EvaluationQuestionDef getEvaluationQuestionDef() {
        return evaluationQuestionDef;
    }

    public void setEvaluationQuestionDef(EvaluationQuestionDef evaluationQuestionDef) {
        this.evaluationQuestionDef = evaluationQuestionDef;
    }

}
