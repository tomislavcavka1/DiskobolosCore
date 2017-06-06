/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model.evaluation;

import hr.diskobolos.model.IIdentifier;
import hr.diskobolos.model.MemberRegister;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "EVALUATION_ANSWER", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "EvaluationAnswer.findAll", query = "SELECT e FROM EvaluationAnswer e"),
    @NamedQuery(name = "EvaluationAnswer.findAllByMemberRegister", query = "SELECT e FROM EvaluationAnswer e WHERE e.memberRegister = :memberRegister")})
public class EvaluationAnswer implements IIdentifier {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.evaluation_answer_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.evaluation_answer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.evaluation_answer_id_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_REGISTER_ID", referencedColumnName = "ID")
    private MemberRegister memberRegister;

    @OneToOne
    @JoinColumn(name = "ANSWER", referencedColumnName = "ID")
    private QuestionChoicesDef answer;

    public EvaluationAnswer() {
    }

    public EvaluationAnswer(Integer id, MemberRegister memberRegister, QuestionChoicesDef answer) {
        this.id = id;
        this.memberRegister = memberRegister;
        this.answer = answer;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MemberRegister getMemberRegister() {
        return memberRegister;
    }

    public void setMemberRegister(MemberRegister memberRegister) {
        this.memberRegister = memberRegister;
    }

    public QuestionChoicesDef getAnswer() {
        return answer;
    }

    public void setAnswer(QuestionChoicesDef answer) {
        this.answer = answer;
    }

}
