/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

import hr.diskobolos.model.MemberRegister;
import java.util.List;

/**
 *
 * @author Tomislav Čavka
 */
public class DashboardDto {

    private Long numberOfSports;

    private Long numberOfMemberRegisters;

    private Long numberOfMembershipCategories;

    private List<TotalPointsPerMemberRegister> rankingTotalPointsPerMemberRegister;

    private List<TotalPointsPerMemberRegister> categorizationTotalPointsPerMemberRegister;

    private TermsOfCompetitionStatistic termsOfCompetitionStatistic;

    private GroupsOfCategorization groupsOfCategorization;

    public Long getNumberOfSports() {
        return numberOfSports;
    }

    public void setNumberOfSports(Long numberOfSports) {
        this.numberOfSports = numberOfSports;
    }

    public Long getNumberOfMemberRegisters() {
        return numberOfMemberRegisters;
    }

    public void setNumberOfMemberRegisters(Long numberOfMemberRegisters) {
        this.numberOfMemberRegisters = numberOfMemberRegisters;
    }

    public Long getNumberOfMembershipCategories() {
        return numberOfMembershipCategories;
    }

    public void setNumberOfMembershipCategories(Long numberOfMembershipCategories) {
        this.numberOfMembershipCategories = numberOfMembershipCategories;
    }

    public List<TotalPointsPerMemberRegister> getRankingTotalPointsPerMemberRegister() {
        return rankingTotalPointsPerMemberRegister;
    }

    public void setRankingTotalPointsPerMemberRegister(List<TotalPointsPerMemberRegister> rankingTotalPointsPerMemberRegister) {
        this.rankingTotalPointsPerMemberRegister = rankingTotalPointsPerMemberRegister;
    }

    public List<TotalPointsPerMemberRegister> getCategorizationTotalPointsPerMemberRegister() {
        return categorizationTotalPointsPerMemberRegister;
    }

    public void setCategorizationTotalPointsPerMemberRegister(List<TotalPointsPerMemberRegister> categorizationTotalPointsPerMemberRegister) {
        this.categorizationTotalPointsPerMemberRegister = categorizationTotalPointsPerMemberRegister;
    }

    public TermsOfCompetitionStatistic getTermsOfCompetitionStatistic() {
        return termsOfCompetitionStatistic;
    }

    public void setTermsOfCompetitionStatistic(TermsOfCompetitionStatistic termsOfCompetitionStatistic) {
        this.termsOfCompetitionStatistic = termsOfCompetitionStatistic;
    }

    public GroupsOfCategorization getGroupsOfCategorization() {
        return groupsOfCategorization;
    }

    public void setGroupsOfCategorization(GroupsOfCategorization groupsOfCategorization) {
        this.groupsOfCategorization = groupsOfCategorization;
    }

    public static class TotalPointsPerMemberRegister {

        private MemberRegister memberRegister;

        private Integer totalPoints;

        public TotalPointsPerMemberRegister(MemberRegister memberRegister, Integer totalPoints) {
            this.memberRegister = memberRegister;
            this.totalPoints = totalPoints;
        }

        public MemberRegister getMemberRegister() {
            return memberRegister;
        }

        public void setMemberRegister(MemberRegister memberRegister) {
            this.memberRegister = memberRegister;
        }

        public Integer getTotalPoints() {
            return totalPoints;
        }

        public void setTotalPoints(Integer totalPoints) {
            this.totalPoints = totalPoints;
        }
    }

    public static class TermsOfCompetitionStatistic {

        private Long numberOfMembersWithValidTerms;

        private Long numberOfMembersWithInvalidTerms;

        private Long numberOfUnfulfilledTerms;

        public Long getNumberOfMembersWithValidTerms() {
            return numberOfMembersWithValidTerms;
        }

        public void setNumberOfMembersWithValidTerms(Long numberOfMembersWithValidTerms) {
            this.numberOfMembersWithValidTerms = numberOfMembersWithValidTerms;
        }

        public Long getNumberOfMembersWithInvalidTerms() {
            return numberOfMembersWithInvalidTerms;
        }

        public void setNumberOfMembersWithInvalidTerms(Long numberOfMembersWithInvalidTerms) {
            this.numberOfMembersWithInvalidTerms = numberOfMembersWithInvalidTerms;
        }

        public Long getNumberOfUnfulfilledTerms() {
            return numberOfUnfulfilledTerms;
        }

        public void setNumberOfUnfulfilledTerms(Long numberOfUnfulfilledTerms) {
            this.numberOfUnfulfilledTerms = numberOfUnfulfilledTerms;
        }
    }

    public static class GroupsOfCategorization {

        private double numberOfMembersFirstCategory;

        private double numberOfMembersSecondCategory;

        private double numberOfMembersThirdCategory;

        private double numberOfMembersFourthCategory;

        public double getNumberOfMembersFirstCategory() {
            return numberOfMembersFirstCategory;
        }

        public void setNumberOfMembersFirstCategory(double numberOfMembersFirstCategory) {
            this.numberOfMembersFirstCategory = numberOfMembersFirstCategory;
        }

        public double getNumberOfMembersSecondCategory() {
            return numberOfMembersSecondCategory;
        }

        public void setNumberOfMembersSecondCategory(double numberOfMembersSecondCategory) {
            this.numberOfMembersSecondCategory = numberOfMembersSecondCategory;
        }

        public double getNumberOfMembersThirdCategory() {
            return numberOfMembersThirdCategory;
        }

        public void setNumberOfMembersThirdCategory(double numberOfMembersThirdCategory) {
            this.numberOfMembersThirdCategory = numberOfMembersThirdCategory;
        }

        public double getNumberOfMembersFourthCategory() {
            return numberOfMembersFourthCategory;
        }

        public void setNumberOfMembersFourthCategory(double numberOfMembersFourthCategory) {
            this.numberOfMembersFourthCategory = numberOfMembersFourthCategory;
        }
    }

}
