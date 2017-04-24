/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "NOMENCLATURE_OF_SPORT", schema = "DISKOBOLOS")
public class NomenclatureOfSport implements INomenclatureOfSport, Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "nomenclature_of_sport_id_seq", sequenceName = "nomenclature_of_sport_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nomenclature_of_sport_id_seq")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private NomenclatureCategories category;

    @Column(name = "CATEGORY_DESCRIPTION")
    private String categoryDescription;

    private String value;

    @ManyToOne
    @JoinColumn(name = "SPORT_ID", referencedColumnName = "ID")
    private Sport sport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NomenclatureCategories getCategory() {
        return category;
    }

    public void setCategory(NomenclatureCategories category) {
        this.category = category;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
