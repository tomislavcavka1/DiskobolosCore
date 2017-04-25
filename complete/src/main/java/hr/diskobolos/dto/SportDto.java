/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

import hr.diskobolos.model.NomenclatureOfSport;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tomislav Cavka
 */
public class SportDto {

    @NotNull
    private Integer id;

    private String name;

    private List<NomenclatureOfSportsDto> nomenclatureOfSports;

    private List<NomenclatureOfSport> removedNomenclatureItems;

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

    public List<NomenclatureOfSportsDto> getNomenclatureOfSports() {
        return nomenclatureOfSports;
    }

    public void setNomenclatureOfSports(List<NomenclatureOfSportsDto> nomenclatureOfSports) {
        this.nomenclatureOfSports = nomenclatureOfSports;
    }

    public List<NomenclatureOfSport> getRemovedNomenclatureItems() {
        return removedNomenclatureItems;
    }

    public void setRemovedNomenclatureItems(List<NomenclatureOfSport> removedNomenclatureItems) {
        this.removedNomenclatureItems = removedNomenclatureItems;
    }
}
