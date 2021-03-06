/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "BANK_ACCOUNT", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "BankAccount.findAll", query = "SELECT b FROM BankAccount b"),
    @NamedQuery(name = "BankAccount.deleteItems", query = "DELETE FROM BankAccount b WHERE b IN :forDeletion")})
public class BankAccount implements IIdentifier {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.bank_account_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.bank_account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.bank_account_id_seq")
    private Integer id;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    private BankAccountType bankAccountType;

    @Column(name = "ACCOUNT_DESCRIPTION")
    private String accountDescription;

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public MemberRegister getMemberRegister() {
        return memberRegister;
    }

    public void setMemberRegister(MemberRegister memberRegister) {
        this.memberRegister = memberRegister;
    }

}
