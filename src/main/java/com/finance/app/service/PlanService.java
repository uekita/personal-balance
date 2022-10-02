package com.finance.app.service;

import java.text.ParseException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.finance.app.domain.model.Transaction;
import com.finance.app.domain.model.TransactionType;
import com.finance.app.domain.repository.PlanRepository;

import com.finance.app.domain.model.Plan;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final TransactionService transactionService;

    public void addPlan(Plan plan) {
        planRepository.save(plan);
    }

    public List<Plan> getPlansByDate(int month, int year) throws ParseException {
        return planRepository.findAllPlansByDate(month,year);
    }

    public List<Plan> getPlansByDateAndType(int month, int year, TransactionType type) throws ParseException {
        List<Plan> plans = planRepository.findAllPlansByDateAndType(month,year, type);
        plans.forEach(p -> {
            try {
                float amount = (float) transactionService.getTransactionsByDateAndTypeAndCategory(String.valueOf(month), String.valueOf(year), p.getType(), p.getCategory())
                        .stream()
                        .mapToDouble(Transaction::getAmount)
                        .sum();
                p.setActual(amount);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return plans;
    }

    public Plan getPlanByDateAndType(int month, int year, TransactionType type) throws ParseException {
        Plan plan = planRepository.findPlanByDateAndType(month,year, type);
        float actual = (float) transactionService.getTransactionsByDateAndType(String.valueOf(month), String.valueOf(year), type)
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        plan.setActual(actual);
        return plan;
    }
}
