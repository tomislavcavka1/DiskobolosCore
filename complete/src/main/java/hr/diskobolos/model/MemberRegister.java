/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hr.diskobolos.dto.PhoneDto;
import hr.diskobolos.model.listener.MemberRegisterListener;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.persistence.Transient;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@EntityListeners({
	MemberRegisterListener.class
})
@Table(name = "MEMBER_REGISTER", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "MemberRegister.findAll", query = "SELECT m FROM MemberRegister m"),
    @NamedQuery(name = "MemberRegister.getNumberOfMemberRegisters", query = "SELECT COUNT(m) FROM MemberRegister m")})
public class MemberRegister implements IIdentifier {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.member_register_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.member_register_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.member_register_id_seq")
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "LOCATION_ID")
    @JsonManagedReference
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberRegister", orphanRemoval = true)
    @JsonManagedReference
    private List<Phone> phones;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MEMBERSHIP_CATEGORY", nullable = true)
    private MembershipCategory membershipCategory;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SPORT_CATEGORY", nullable = true)
    private Sport sportCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberRegister", orphanRemoval = true)
    @JsonManagedReference
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberRegister", orphanRemoval = true)
    @JsonManagedReference
    private List<BankAccount> bankAccounts;

    @Transient
    private List<PhoneDto> phonesDto;

    @Transient
    private List<Phone> removedPhones;

    @Transient
    private List<Email> removedEmails;

    @Transient
    private List<BankAccount> removedBankAccounts;

    @Override
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
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

    public Sport getSportCategory() {
        return sportCategory;
    }

    public void setSportCategory(Sport sportCategory) {
        this.sportCategory = sportCategory;
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

    public List<PhoneDto> getPhonesDto() {
        return phonesDto;
    }

    public void setPhonesDto(List<PhoneDto> phonesDto) {
        this.phonesDto = phonesDto;
    }

    public List<Phone> getRemovedPhones() {
        return removedPhones;
    }

    public void setRemovedPhones(List<Phone> removedPhones) {
        this.removedPhones = removedPhones;
    }

    public List<Email> getRemovedEmails() {
        return removedEmails;
    }

    public void setRemovedEmails(List<Email> removedEmails) {
        this.removedEmails = removedEmails;
    }

    public List<BankAccount> getRemovedBankAccounts() {
        return removedBankAccounts;
    }

    public void setRemovedBankAccounts(List<BankAccount> removedBankAccounts) {
        this.removedBankAccounts = removedBankAccounts;
    }
}
