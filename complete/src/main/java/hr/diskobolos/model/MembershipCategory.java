/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tomislav Čavka
 */
@Entity
@Table(name = "MEMBERSHIP_CATEGORY", schema = "DISKOBOLOS")
@NamedQueries({
    @NamedQuery(name = "MembershipCategory.findAll", query = "SELECT m FROM MembershipCategory m"),
    @NamedQuery(name = "MembershipCategory.getNumberOfMembershipCategories", query = "SELECT COUNT(m) FROM MembershipCategory m")})
public class MembershipCategory implements IIdentifier {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.membership_category_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.membership_category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.membership_category_id_seq")
    private Integer id;

    private String description;

    @Column(name = "HTML_COLOR")
    private String htmlColor;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlColor() {
        return htmlColor;
    }

    public void setHtmlColor(String htmlColor) {
        this.htmlColor = htmlColor;
    }

}
