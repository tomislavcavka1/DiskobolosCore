/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.dto.DashboardDto;
import hr.diskobolos.dto.IDashboardDto;
import hr.diskobolos.model.MemberRegister;
import hr.diskobolos.model.evaluation.QuestionnaireType;
import hr.diskobolos.model.evaluation.TermsOfConditionStatus;
import hr.diskobolos.persistence.IEvaluationAnswerPersistence;
import hr.diskobolos.persistence.IMemberRegisterPersistence;
import hr.diskobolos.persistence.IMembershipCategoryPersistence;
import hr.diskobolos.persistence.ISportPersistence;
import hr.diskobolos.service.IDashboardService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class DashboardServiceImpl implements IDashboardService {

    @Autowired
    ISportPersistence sportPersistence;

    @Autowired
    IMemberRegisterPersistence memberRegisterPersistence;

    @Autowired
    IMembershipCategoryPersistence membershipCategoryPersistence;

    @Autowired
    IEvaluationAnswerPersistence evalautionAnswerPersistence;

    @Override
    public DashboardDto fetchDashboardData() {
        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setNumberOfSports(sportPersistence.getNumberOfSports());
        dashboardDto.setNumberOfMemberRegisters(memberRegisterPersistence.getNumberOfMemberRegisters());
        dashboardDto.setNumberOfMembershipCategories(membershipCategoryPersistence.getNumberOfMembershipCategories());
        Map<MemberRegister, Integer> rankingTotalPointsPerMemberRegister = evalautionAnswerPersistence.fetchTotalPointsPerMemberRegister(QuestionnaireType.RANKING_AND_CATEGORIZATION_OF_SPORTS);
        List<DashboardDto.TotalPointsPerMemberRegister> rankingTotalPointsPerMemberRegisterList
                = rankingTotalPointsPerMemberRegister.entrySet()
                .stream()
                .map(e -> new DashboardDto.TotalPointsPerMemberRegister(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        dashboardDto.setRankingTotalPointsPerMemberRegister(rankingTotalPointsPerMemberRegisterList);
        Map<MemberRegister, Integer> categorizationTotalPointsPerMemberRegister = evalautionAnswerPersistence.fetchTotalPointsPerMemberRegister(QuestionnaireType.CATEGORIZATION_OF_SPORTS_PER_SPORT_CLUB);
        List<DashboardDto.TotalPointsPerMemberRegister> categorizationTotalPointsPerMemberRegisterList
                = categorizationTotalPointsPerMemberRegister.entrySet()
                .stream()
                .map(e -> new DashboardDto.TotalPointsPerMemberRegister(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        dashboardDto.setCategorizationTotalPointsPerMemberRegister(categorizationTotalPointsPerMemberRegisterList);
        dashboardDto.setTermsOfCompetitionStatistic(fetchTermsOfCompetitionStatistic());
        dashboardDto.setGroupsOfCategorization(fetchGroupsOfCategorization(categorizationTotalPointsPerMemberRegister));
        return dashboardDto;
    }

    private DashboardDto.TermsOfCompetitionStatistic fetchTermsOfCompetitionStatistic() {

        DashboardDto.TermsOfCompetitionStatistic termsOfCompetitionStatisticData = new DashboardDto.TermsOfCompetitionStatistic();
        long numberOfMemberRegistersWithoutTerms = evalautionAnswerPersistence.fetchNumberOfMemberRegistersWithoutTerms();
        ConcurrentMap<TermsOfConditionStatus, AtomicLong> termsOfCompetitionStatistic = evalautionAnswerPersistence.fetchTermsOfCompetitionStatistic();

        long totalNumberOfUnfulfilledQuestionnaires = (termsOfCompetitionStatistic.get(TermsOfConditionStatus.NONE) != null ? termsOfCompetitionStatistic.get(TermsOfConditionStatus.NONE).longValue() : 0) + numberOfMemberRegistersWithoutTerms;
        termsOfCompetitionStatisticData.setNumberOfUnfulfilledTerms(totalNumberOfUnfulfilledQuestionnaires);
        termsOfCompetitionStatisticData.setNumberOfMembersWithValidTerms(termsOfCompetitionStatistic.get(TermsOfConditionStatus.VALID).longValue());
        termsOfCompetitionStatisticData.setNumberOfMembersWithInvalidTerms(termsOfCompetitionStatistic.get(TermsOfConditionStatus.INVALID).longValue());
        return termsOfCompetitionStatisticData;
    }

    private DashboardDto.GroupsOfCategorization fetchGroupsOfCategorization(Map<MemberRegister, Integer> categorizationTotalPointsPerMemberRegister) {

        int totalPoints = categorizationTotalPointsPerMemberRegister.entrySet()
                .stream().collect(Collectors.summingInt(c -> c.getValue()));

        Map<IDashboardDto.CategorizationOfSportsGroup, Integer> categorizationOfSportsGroups = categorizationTotalPointsPerMemberRegister.entrySet()
                .stream()
                .collect(Collectors.groupingBy(t -> {
                    int value = t.getValue();
                    IDashboardDto.CategorizationOfSportsGroup categorizationOfSportsGroup = null;
                    if (IDashboardDto.CategorizationOfSportsGroup.CATEGORY_1.getFrom() <= value
                            && value <= IDashboardDto.CategorizationOfSportsGroup.CATEGORY_1.getTo()) {
                        categorizationOfSportsGroup = IDashboardDto.CategorizationOfSportsGroup.CATEGORY_1;
                    } else if (IDashboardDto.CategorizationOfSportsGroup.CATEGORY_2.getFrom() <= value
                            && value <= IDashboardDto.CategorizationOfSportsGroup.CATEGORY_2.getTo()) {
                        categorizationOfSportsGroup = IDashboardDto.CategorizationOfSportsGroup.CATEGORY_2;
                    } else if (IDashboardDto.CategorizationOfSportsGroup.CATEGORY_3.getFrom() <= value
                            && value <= IDashboardDto.CategorizationOfSportsGroup.CATEGORY_3.getTo()) {
                        categorizationOfSportsGroup = IDashboardDto.CategorizationOfSportsGroup.CATEGORY_3;
                    } else if (IDashboardDto.CategorizationOfSportsGroup.CATEGORY_4.getFrom() <= value
                            && value <= IDashboardDto.CategorizationOfSportsGroup.CATEGORY_4.getTo()) {
                        categorizationOfSportsGroup = IDashboardDto.CategorizationOfSportsGroup.CATEGORY_4;
                    }
                    return categorizationOfSportsGroup;
                }, Collectors.summingInt(t -> t.getValue())));

        DashboardDto.GroupsOfCategorization groupsOfCategorization = new DashboardDto.GroupsOfCategorization();
        groupsOfCategorization.setNumberOfMembersFirstCategory(calculatePercentage(totalPoints, categorizationOfSportsGroups.get(IDashboardDto.CategorizationOfSportsGroup.CATEGORY_1)));
        groupsOfCategorization.setNumberOfMembersSecondCategory(calculatePercentage(totalPoints, categorizationOfSportsGroups.get(IDashboardDto.CategorizationOfSportsGroup.CATEGORY_2)));
        groupsOfCategorization.setNumberOfMembersThirdCategory(calculatePercentage(totalPoints, categorizationOfSportsGroups.get(IDashboardDto.CategorizationOfSportsGroup.CATEGORY_3)));
        groupsOfCategorization.setNumberOfMembersFourthCategory(calculatePercentage(totalPoints, categorizationOfSportsGroups.get(IDashboardDto.CategorizationOfSportsGroup.CATEGORY_4)));
        return groupsOfCategorization;
    }

    private double calculatePercentage(Integer totalPoints, Integer categoryPoints) {
        if (categoryPoints == null) {
            return 0.0;
        }
        return BigDecimal.valueOf((double) (categoryPoints * 100) / (double) totalPoints).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
