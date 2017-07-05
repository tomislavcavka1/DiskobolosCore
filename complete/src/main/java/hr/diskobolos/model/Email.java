/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "EMAIL", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e"),
    @NamedQuery(name = "Email.deleteItems", query = "DELETE FROM Email e WHERE e IN :forDeletion")})
public class Email implements IIdentifier {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.email_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.email_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.email_id_seq")
    private Integer id;

    private String email;

    @ManyToOne
    @JoinColumn(name = "MEMBER_REGISTER_ID", referencedColumnName = "ID")
    @JsonBackReference
    private MemberRegister memberRegister;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberRegister getMemberRegister() {
        return memberRegister;
    }

    public void setMemberRegister(MemberRegister memberRegister) {
        this.memberRegister = memberRegister;
    }

}
