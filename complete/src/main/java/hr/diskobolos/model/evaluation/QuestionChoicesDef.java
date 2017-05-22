/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class QuestionChoicesDef implements Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "question_choices_def_id_seq", sequenceName = "question_choices_def_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_choices_def_id_seq")
    private Integer id;

    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
