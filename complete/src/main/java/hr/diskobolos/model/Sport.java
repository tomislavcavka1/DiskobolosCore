/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "SPORT", schema = "DISKOBOLOS")
@NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s")
public class Sport implements ISport, Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "sport_id_seq", sequenceName = "sport_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sport_id_seq")
    private Integer id;

    private String name;

    @OneToMany(targetEntity = NomenclatureOfSport.class, cascade = CascadeType.ALL, mappedBy = "sport", orphanRemoval = true)
    private List<NomenclatureOfSport> nomenclatureOfSports;

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

    public List<NomenclatureOfSport> getNomenclatureOfSports() {
        return nomenclatureOfSports;
    }

    public void setNomenclatureOfSports(List<NomenclatureOfSport> nomenclatureOfSports) {
        this.nomenclatureOfSports = nomenclatureOfSports;
    }
}
