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
@Table(name = "LOCATION", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByAddress", query = "SELECT l FROM Location l WHERE l.address = :address")})
public class Location implements IIdentifier {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.location_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.location_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.location_id_seq")
    private Integer id;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    private String address;

    private Float latitude;

    private Float longitude;

    @Column(name = "POSTAL_CODE")
    private Integer postalCode;

    private String city;

    @OneToOne(mappedBy = "location")
    @JsonBackReference
    private MemberRegister memberRegister;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public MemberRegister getMemberRegister() {
        return memberRegister;
    }

    public void setMemberRegister(MemberRegister memberRegister) {
        this.memberRegister = memberRegister;
    }
}
