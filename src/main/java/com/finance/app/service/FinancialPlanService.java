package com.finance.app.service;

import java.text.ParseException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.finance.app.domain.model.FinancialSummary;
import com.finance.app.domain.model.FinancialPlan;
import com.finance.app.domain.model.Plan;
import com.finance.app.domain.model.TransactionType;

@Service
@RequiredArgsConstructor
public class FinancialPlanService {

    private final PlanService planService;


    public FinancialSummary getFinancialSummary(int month, int year) throws ParseException {
        List<Plan> incomes = planService.getPlansByDateAndType(month, year, TransactionType.INCOME);
        List<Plan> expenses = planService.getPlansByDateAndType(month, year, TransactionType.EXPENSE);

        float plannedIncomes = (float) incomes.stream()
                .mapToDouble(Plan::getPlanned).sum();

        float plannedExpenses = (float) expenses.stream()
                .mapToDouble(Plan::getPlanned).sum();

        float actualIncomes = (float) incomes.stream()
                .mapToDouble(Plan::getActual).sum();

        float actualExpenses = (float) expenses.stream()
                .mapToDouble(Plan::getActual).sum();

        return FinancialSummary.builder()
                .incomes(FinancialPlan.builder()
                        .plans(incomes)
                        .planned(plannedIncomes)
                        .actual(actualIncomes)
                        .build())
                .expenses(FinancialPlan.builder()
                        .plans(expenses)
                        .planned(plannedExpenses)
                        .actual(actualExpenses)
                        .build())
                .build();
    }
}
