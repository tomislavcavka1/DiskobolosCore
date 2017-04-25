/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "MEMBER_REGISTER", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "MemberRegister.findAll", query = "SELECT m FROM MemberRegister m")})
public class MemberRegister implements Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "member_register_id_seq", sequenceName = "member_register_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_register_id_seq")
    private Integer id;

    private String name;

    private String address;

    @OneToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    private String phone1;

    private String phone2;

    private String fax;

    @Column(name = "IDENTIFICATION_NUMBER")
    private String identificationNumber;

    private String oib;

    @Column(name = "REGISTER_NUMBER")
    private String registerNumber;

    @Column(name = "NUMBER_OF_NON_PROFIT_ORG")
    private String numberOfNonProfitOrg;

    private String chairman;

    private String secretary;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "DATE_TO")
    private Date dateTo;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @OneToOne
    @JoinColumn(name = "MEMBERSHIP_CATEGORY")
    private MembershipCategory membershipCategory;

    @OneToMany(orphanRemoval=true)
    @JoinColumn(name = "MEMBER_REGISTER_ID")
    private List<Email> emails;

    @OneToMany(orphanRemoval=true)
    @JoinColumn(name = "MEMBER_REGISTER_ID")
    private List<BankAccount> bankAccounts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getNumberOfNonProfitOrg() {
        return numberOfNonProfitOrg;
    }

    public void setNumberOfNonProfitOrg(String numberOfNonProfitOrg) {
        this.numberOfNonProfitOrg = numberOfNonProfitOrg;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public MembershipCategory getMembershipCategory() {
        return membershipCategory;
    }

    public void setMembershipCategory(MembershipCategory membershipCategory) {
        this.membershipCategory = membershipCategory;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

}
