package com.finance.app.controller;

import java.text.ParseException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finance.app.domain.model.Plan;
import com.finance.app.service.PlanService;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PutMapping("api/v1/plan")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPlan(@RequestBody Plan plan) {
        planService.addPlan(plan);
    }

    @GetMapping("api/v1/plan/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<Plan> getPlans(@PathVariable("month") int month,
                               @PathVariable("year") int year) throws ParseException {
        return planService.getPlansByDate(month, year);
    }
}
