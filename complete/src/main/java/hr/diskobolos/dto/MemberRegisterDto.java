/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

import hr.diskobolos.model.BankAccount;
import hr.diskobolos.model.Email;
import hr.diskobolos.model.Location;
import hr.diskobolos.model.MembershipCategory;
import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public class MemberRegisterDto {

    private Integer id;

    private String name;

    private String address;

    private Location location;

    private String phone1;

    private String phone2;

    private String fax;

    private String identificationNumber;

    private String oib;

    private String registerNumber;

    private String numberOfNonProfitOrg;

    private String chairman;

    private String secretary;

    private String dateFrom;

    private String dateTo;

    private String registrationDate;

    private MembershipCategory membershipCategory;

    private List<Email> emails;

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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
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
