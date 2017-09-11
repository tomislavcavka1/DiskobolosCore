/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "FINANCIAL_RESOURCES", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "FinancialResources.findAll", query = "SELECT f FROM FinancialResources f")})
public class FinancialResources implements IIdentifier {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.financial_resources_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.financial_resources_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.financial_resources_id_seq")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "MEMBER_REGISTER_ID", referencedColumnName = "ID")
    private MemberRegister memberRegister;

    private Double amount;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "LAST_UPDATE_ON")
    private Date lastUpdateOn;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    @PreUpdate
    @PrePersist
    public void beforeUpdate() {
        setLastUpdateOn(new Date());
    }

}
