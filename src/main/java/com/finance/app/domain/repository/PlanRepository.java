package com.finance.app.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finance.app.domain.model.Plan;
import com.finance.app.domain.model.TransactionType;

@Repository
public interface PlanRepository extends JpaRepository<Plan, UUID> {

    @Query("SELECT p FROM Plan " +
            "p WHERE p.month = :month " +
            "AND p.year = :year")
    List<Plan> findAllPlansByDate(
            @Param("month") int month,
            @Param("year") int year);

    @Query("SELECT p FROM Plan " +
            "p WHERE p.month = :month " +
            "AND p.type = :type " +
            "AND p.year = :year")
    List<Plan> findAllPlansByDateAndType(
            @Param("month") int month,
            @Param("year") int year,
           @Param("type") TransactionType type);

    @Query("SELECT p FROM Plan " +
            "p WHERE p.month = :month " +
            "AND p.type = :type " +
            "AND p.year = :year")
    Plan findPlanByDateAndType(
            @Param("month") int month,
            @Param("year") int year,
            @Param("type") TransactionType type);
}
