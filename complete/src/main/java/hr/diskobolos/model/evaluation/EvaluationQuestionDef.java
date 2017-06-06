/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

import hr.diskobolos.model.IIdentifier;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "EVALUATION_QUESTION_DEF", schema = "DISKOBOLOS", uniqueConstraints = @UniqueConstraint(columnNames = {"QUESTION"}))
@NamedQuery(name = "EvaluationQuestionDef.findAll", query = "SELECT e FROM EvaluationQuestionDef e")
public class EvaluationQuestionDef implements IIdentifier, Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.evaluation_question_def_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.evaluation_question_def_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.evaluation_question_def_id_seq")
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    private EvaluationQuestionnaireDefEnum question;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "VALUE_TYPE")
    private QuestionValueType valueType;

    private Boolean mandatory;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @OneToMany(mappedBy = "evaluationQuestionDef")
    private List<QuestionChoicesDef> choices;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvaluationQuestionnaireDefEnum getQuestion() {
        return question;
    }

    public void setQuestion(EvaluationQuestionnaireDefEnum question) {
        this.question = question;
    }

    public QuestionValueType getValueType() {
        return valueType;
    }

    public void setValueType(QuestionValueType valueType) {
        this.valueType = valueType;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<QuestionChoicesDef> getChoices() {
        return choices;
    }

    public void setChoices(List<QuestionChoicesDef> choices) {
        this.choices = choices;
    }
}
