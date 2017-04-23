/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

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
@Table(name = "EMAIL", schema = "DISKOBOLOS")
@NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
public class Email implements Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "email_id_seq", sequenceName = "email_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_id_seq")
    private Integer id;

    private String email;

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

}
