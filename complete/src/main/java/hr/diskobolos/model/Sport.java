/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
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
@NamedQueries({
    @NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s WHERE s.name IS NOT NULL"),
    @NamedQuery(name = "Sport.getNumberOfSports", query = "SELECT COUNT(s) FROM Sport s WHERE s.name IS NOT NULL"),
    @NamedQuery(name = "Sport.findSportByName", query = "SELECT s FROM Sport s WHERE s.name = :name")})
public class Sport implements ISport {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.sport_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.sport_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.sport_id_seq")
    private Integer id;

    private String name;

    @OneToMany(targetEntity = NomenclatureOfSport.class, cascade = CascadeType.ALL, mappedBy = "sport", orphanRemoval = true)
    @JsonManagedReference
    private List<NomenclatureOfSport> nomenclatureOfSports;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<NomenclatureOfSport> getNomenclatureOfSports() {
        return nomenclatureOfSports;
    }

    public void setNomenclatureOfSports(List<NomenclatureOfSport> nomenclatureOfSports) {
        this.nomenclatureOfSports = nomenclatureOfSports;
    }
}
