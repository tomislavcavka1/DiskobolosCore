/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

/**
 *
 * @author Tomislav Čavka
 */
public class CategorizationOfSportsPerSportClubDto {

    private Integer id;

    private String name;

    private Integer categorizationOnNationalFederationLevel;

    private Integer categorizationOnSportTypeLevel;

    private Integer categorizationAccordingToAge;

    private Integer categorizationOnCompetitionSystemLevel;

    private Integer categorizationBasedOnNumberOfTeamMembersCompeting;

    private Integer categorizationAccordingToMassInSportsSchools;

    private Integer categorizationAccordingToProfessionalStaff;

    private Integer categorizationAccordingToTraditionOfTownZadar;

    private Integer cofficiencyOfSportsCategory;

    private Integer totalPoints;

    private Float questionnairePercentage;

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

    public Integer getCategorizationOnNationalFederationLevel() {
        return categorizationOnNationalFederationLevel;
    }

    public void setCategorizationOnNationalFederationLevel(Integer categorizationOnNationalFederationLevel) {
        this.categorizationOnNationalFederationLevel = categorizationOnNationalFederationLevel;
    }

    public Integer getCategorizationOnSportTypeLevel() {
        return categorizationOnSportTypeLevel;
    }

    public void setCategorizationOnSportTypeLevel(Integer categorizationOnSportTypeLevel) {
        this.categorizationOnSportTypeLevel = categorizationOnSportTypeLevel;
    }

    public Integer getCategorizationAccordingToAge() {
        return categorizationAccordingToAge;
    }

    public void setCategorizationAccordingToAge(Integer categorizationAccordingToAge) {
        this.categorizationAccordingToAge = categorizationAccordingToAge;
    }

    public Integer getCategorizationOnCompetitionSystemLevel() {
        return categorizationOnCompetitionSystemLevel;
    }

    public void setCategorizationOnCompetitionSystemLevel(Integer categorizationOnCompetitionSystemLevel) {
        this.categorizationOnCompetitionSystemLevel = categorizationOnCompetitionSystemLevel;
    }

    public Integer getCategorizationBasedOnNumberOfTeamMembersCompeting() {
        return categorizationBasedOnNumberOfTeamMembersCompeting;
    }

    public void setCategorizationBasedOnNumberOfTeamMembersCompeting(Integer categorizationBasedOnNumberOfTeamMembersCompeting) {
        this.categorizationBasedOnNumberOfTeamMembersCompeting = categorizationBasedOnNumberOfTeamMembersCompeting;
    }

    public Integer getCategorizationAccordingToMassInSportsSchools() {
        return categorizationAccordingToMassInSportsSchools;
    }

    public void setCategorizationAccordingToMassInSportsSchools(Integer categorizationAccordingToMassInSportsSchools) {
        this.categorizationAccordingToMassInSportsSchools = categorizationAccordingToMassInSportsSchools;
    }

    public Integer getCategorizationAccordingToProfessionalStaff() {
        return categorizationAccordingToProfessionalStaff;
    }

    public void setCategorizationAccordingToProfessionalStaff(Integer categorizationAccordingToProfessionalStaff) {
        this.categorizationAccordingToProfessionalStaff = categorizationAccordingToProfessionalStaff;
    }

    public Integer getCategorizationAccordingToTraditionOfTownZadar() {
        return categorizationAccordingToTraditionOfTownZadar;
    }

    public void setCategorizationAccordingToTraditionOfTownZadar(Integer categorizationAccordingToTraditionOfTownZadar) {
        this.categorizationAccordingToTraditionOfTownZadar = categorizationAccordingToTraditionOfTownZadar;
    }

    public Integer getCofficiencyOfSportsCategory() {
        return cofficiencyOfSportsCategory;
    }

    public void setCofficiencyOfSportsCategory(Integer cofficiencyOfSportsCategory) {
        this.cofficiencyOfSportsCategory = cofficiencyOfSportsCategory;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Float getQuestionnairePercentage() {
        return questionnairePercentage;
    }

    public void setQuestionnairePercentage(Float questionnairePercentage) {
        this.questionnairePercentage = questionnairePercentage;
    }

}
