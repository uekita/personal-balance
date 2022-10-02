package com.finance.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.finance.app.domain.model.FinancialSummary;
import com.finance.app.service.FinancialPlanService;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class FinancialPlanController {

    private final FinancialPlanService financialPlanService;

    @GetMapping("api/v1/financialSummary/{month}/{year}")
    public FinancialSummary getFinancialSummary(@PathVariable("month") int month,
                                                @PathVariable("year") int year) throws ParseException {
        return financialPlanService.getFinancialSummary(month, year);
    }
}
